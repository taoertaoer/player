package com.tao.player.util.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
//import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;


public class JdbcUtil {
	private static Connection conn = null;

	private static String driver = null;
	private static String url = null;
	private static String username = null;
	private static String password = null;
	static {
		InputStream is = JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties");
		Properties p = new Properties();
		try {
			p.load(is);
			driver = p.getProperty("db.driverClass");//"com.mysql.jdbc.Driver";
			url = p.getProperty("db.jdbcUrl");//"jdbc:mysql://192.168.18.128:3306/player?characterEncoding=utf-8&useSSL=true";
			username = p.getProperty("db.username");//"root";
			password = p.getProperty("db.password");//"";
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			p.clear();
		}
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
	}
	
	public static Connection getConn() {
		try {
			if(conn == null || conn.isClosed()) {
				synchronized (JdbcUtil.class) {				
					if (conn == null || conn.isClosed()) {
						conn = DriverManager.getConnection(url, username, password);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void close() {
		try {
			getConn().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void commit() {
		try {
			getConn().commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void rollback() {
		try {
			getConn().rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<Map<String, Object>> get(String sql){
		return get(sql, null);
	}
	
	public static int insertOrUpdate(String sql, Object[] list) {
		PreparedStatement stmt = null;
		int result = 0;
		Connection conn = getConn();
		try {
			stmt = conn.prepareStatement(sql);
				conn.setAutoCommit(false);
				for (int j = 0; j < list.length; j++) {
					Object os = list[j];
					stmt.setObject(j + 1, os);
				}
				result=stmt.executeUpdate();
				conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}
	
	public static int[] batchInsert(String sql, List<Object[]> list) {
		PreparedStatement stmt = null;
		int[] result = null;
		Connection conn = getConn();
		try {
			stmt = conn.prepareStatement(sql);
			if ((list != null) && (list.size() > 0)) {
				conn.setAutoCommit(false);
				for (int i = 0; i < list.size(); i++) {
					for (int j = 0; j < list.get(i).length; j++) {
						Object[] os = list.get(i);
						stmt.setObject(j + 1, os[j]);
					}
					stmt.addBatch();
				}
				result=stmt.executeBatch();
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}
	
	public static String getTableName(Class<?> clz) {
		if(clz.isAnnotationPresent(JdbcTableName.class)) {
			JdbcTableName jdbcTableName = (JdbcTableName) clz.getAnnotation(JdbcTableName.class);
			return jdbcTableName.name();
		}
		return null;
	}
	
	/**
	 * @param clz
	 * @return
	 * Key fieldName
	 * Value columnName
	 */
	public static Map<String, String> getClumnName(Class<?> clz){
		Map<String, String>	map = new HashMap<String, String>();
		
		Field[] fs = clz.getDeclaredFields();
		for (int i = 0; i < fs.length; i++) {
			Field field = fs[i];
			
			if(field.isAnnotationPresent(JdbcColumeName.class)) {
				JdbcColumeName jdbcColumeName = (JdbcColumeName) field.getAnnotation(JdbcColumeName.class);
				if("".equals(jdbcColumeName.name())) {
					map.put(field.getName(), field.getName());
				}else {
					map.put(field.getName(), jdbcColumeName.name());
				}
			}
		}
		return map;
	}
	
	public static <T> List<T> findAll(Class<T> clz) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException{
		return findByMap(clz, null, null);
	}
	
	public static <T> int add(T t) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		PreparedStatement stmt = null;
		int result = 0;
		Connection conn = getConn();
		try {
			Map<String, String> map = getClumnName(t.getClass());
			List<String> fields = new ArrayList<>();
			List<String> columns = new ArrayList<>();
			for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext();) {
				String field = iterator.next();
				String column = map.get(field);
				fields.add(field);
				columns.add(column);
			}
			StringBuffer sql = new StringBuffer("insert into ");
			sql.append(getTableName(t.getClass()));
			sql.append(" (");
			sql.append(StringUtils.join(columns,","));
			sql.append(") values (");
			List<String> q = new ArrayList<String>();
			for(int i = 0; i < fields.size(); i++) {q.add("?");};
			sql.append(StringUtils.join(q,","));
			sql.append(") ");
			stmt = conn.prepareStatement(sql.toString());
			for (int i = 0; i < fields.size(); i++) {
				String method = "get"+fields.get(i).substring(0,1).toUpperCase()+fields.get(i).substring(1);
				Object value = t.getClass().getMethod(method).invoke(t);
				stmt.setObject(i+1, value);
			}
			//System.out.println(t);
			result=stmt.executeUpdate();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}
	
	public static <T> int delete(T t) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		PreparedStatement stmt = null;
		int result = 0;
		Connection conn = getConn();
		try {
			Map<String, String> map = getClumnName(t.getClass());
			List<String> fields = new ArrayList<>();
			List<String> columns = new ArrayList<>();
			for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext();) {
				String field = iterator.next();
				String column = map.get(field);
				fields.add(field);
				columns.add(column);
			}
			StringBuffer sql = new StringBuffer("delete from ");
			sql.append(getTableName(t.getClass()));
			sql.append(" where 1 = 1 ");
			for (Iterator<String> iterator = columns.iterator(); iterator.hasNext();) {
				String column = iterator.next();
				sql.append(" and ");
				sql.append(map.get(column));
				if(iterator.hasNext()) {
					sql.append("=?, ");
				}else {
					sql.append("=? ");
				}
			}
			stmt = conn.prepareStatement(sql.toString());
			for (int i = 0; i < fields.size(); i++) {
				String method = "get"+fields.get(i).substring(0,1).toUpperCase()+fields.get(i).substring(1);
				stmt.setObject(i+1, t.getClass().getMethod(method).invoke(t));
			}
			result=stmt.executeUpdate();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @param <T>
	 * @param clz
	 * @param parameters
	 * key field
	 * value value
	 * @param orderParameter
	 * column
	 * @return
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static <T> List<T> findByMap(Class<T> clz, Map<String, Object> parameters, String orderParameter) throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException{
		List<T> list = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet result = null;
		Connection conn = getConn();
		try {
			StringBuffer sql = new StringBuffer("select ");
			Map<String, String> map = getClumnName(clz);
			sql.append(StringUtils.join(map.values(),","));
			sql.append(" from ");
			sql.append(getTableName(clz));
			
			List<String> columns = new ArrayList<>(); 
			if(parameters!=null && parameters.keySet().size()>0) {
				sql.append(" where 1=1 ");
				for (Iterator<String> iterator = parameters.keySet().iterator(); iterator.hasNext();) {
					String column = iterator.next();
					sql.append(" and ");
					sql.append(map.get(column));
					sql.append(" = ? ");
					columns.add(column);
				}
			}
			if(orderParameter!=null) {
				sql.append(orderParameter);
			}
			stmt = conn.prepareStatement(sql.toString());
			for (int i = 0; i < columns.size(); i++) {
				stmt.setObject(i+1, parameters.get(columns.get(i)));
			}
			result=stmt.executeQuery();
			while(result.next()) {
				T t = clz.newInstance();
				for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext();) {
					String field = iterator.next();
					Class<?> type=clz.getDeclaredField(field).getType();
					Object object =result.getObject(map.get(field));
//					if(object instanceof oracle.sql.CLOB) {
//						Clob c=(Clob)object;
//						object = c.getSubString(1, (int)c.length());
//					}
					clz.getDeclaredMethod("set"+field.substring(0,1).toUpperCase()+field.substring(1), type).invoke(t, object);
				}
				list.add(t);
			}
		} finally {
			if(result != null) {
				try {
					result.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		return list;
	}
	
	/**
	 * 
	 * @param <T>
	 * @param t
	 * @param values
	 * key field
	 * value value
	 * @param conditions
	 * key field
	 * value value
	 * @return
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public static <T> int updateByMap(Class<T> clz, Map<String, Object> values, Map<String, Object> conditions) throws SQLException{
		PreparedStatement stmt = null;
		int result = 0;
		Connection conn = getConn();
		try {
			List<String> values_columns = new ArrayList<>();
			List<String> conditions_columns = new ArrayList<>();
			System.out.println("values====="+values);
			System.out.println("conditions======="+conditions);
			Map<String, String> columnMap = getClumnName(clz);
			StringBuffer sql = new StringBuffer("update ");
			sql.append(getTableName(clz));
			sql.append(" set ");
			for (Iterator<String> iterator = values.keySet().iterator(); iterator.hasNext();) {
				String column = iterator.next();
				sql.append(columnMap.get(column));
				if(iterator.hasNext()) {
					sql.append("=?, ");
				}else {
					sql.append("=? ");
				}
				values_columns.add(column);
			}
			
			if(conditions!=null && conditions.keySet().size()>0) {
				sql.append(" where 1=1 ");
				for (Iterator<String> iterator = conditions.keySet().iterator(); iterator.hasNext();) {
					String column = iterator.next();
					sql.append(" and ");
					sql.append(columnMap.get(column));
					sql.append(" = ? ");
					conditions_columns.add(column);
				}
			}
			System.out.println("sql===="+sql.toString());
			stmt = conn.prepareStatement(sql.toString());
			for (int i = 0; i < values_columns.size(); i++) {
				stmt.setObject(i+1, values.get(values_columns.get(i)));
			}
			for (int i = 0; i < conditions_columns.size(); i++) {
				stmt.setObject(i+1+values_columns.size(), conditions.get(conditions_columns.get(i)));
			}
			result=stmt.executeUpdate();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @param <T>
	 * @param clz
	 * @param parameters
	 * key field
	 * value value
	 * @return
	 * @throws SQLException
	 */
	public static <T> int deleteByMap(Class<T> clz, Map<String, Object> parameters) throws SQLException{
		PreparedStatement stmt = null;
		int result = 0;
		Connection conn = getConn();
		try {
			List<String> columns = new ArrayList<>();
			
			Map<String, String> columnMap = getClumnName(clz);
			StringBuffer sql = new StringBuffer("delete from ");
			sql.append(getTableName(clz));
			if(parameters!=null && parameters.keySet().size()>0) {
				sql.append(" where 1=1 ");
				for (Iterator<String> iterator = parameters.keySet().iterator(); iterator.hasNext();) {
					String column = iterator.next();
					sql.append(" and ");
					sql.append(columnMap.get(column));
					sql.append(" = ? ");
					columns.add(column);
				}
			}
			stmt = conn.prepareStatement(sql.toString());
			for (int i = 0; i < columns.size(); i++) {
				stmt.setObject(i+1, parameters.get(columns.get(i)));
			}
			result=stmt.executeUpdate();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @param sql
	 * @param parameters
	 * @param pageMap 
	 * key (String)start|limit
	 * value (Integer) 
	 * @return
	 */
	public static List<Map<String, Object>> getListWithOraclePaging(String sql, List<Object> parameters, Map<String, Integer> pageMap){
		Integer page1 = pageMap.get("start")+1;
		Integer rows = pageMap.get("limit");
		sql ="select * from(select a.*,rownum rn from (  "+sql+" ) a where rownum < "+(page1*rows+1)+") where rn>"+((page1-1)*rows)+" ";
		List<Map<String, Object>> list = JdbcUtil.get(sql, parameters);
		return list;
	}
	
	/**
	 * 
	 * @param sql
	 * @param parameters
	 * @param pageMap 
	 * key (String)start|limit
	 * value (Integer) 
	 * @return
	 */
	public static List<Map<String, Object>> getListWithMysqlPaging(String sql, List<Object> parameters, Map<String, Integer> pageMap){
		Integer page1 = pageMap.get("start")+1;
		Integer rows = pageMap.get("limit");
		sql =sql+" limit "+page1+","+rows+" ";
		System.out.println("sql="+sql);
		List<Map<String, Object>> list = JdbcUtil.get(sql, parameters);
		return list;
	}
	
	public static List<Map<String, Object>> get(String sql, List<Object> parameters){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = getConn();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			
			if(parameters!=null && parameters.size()>0) {
				for (int i = 0; i < parameters.size(); i++) {
					stmt.setObject(i+1, parameters.get(i));
				}
			}
			rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					Object b = rs.getObject(rsmd.getColumnName(i));
					map.put(rs.getMetaData().getColumnName(i), b);
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public static void main(String[] args) {}
}
