package com.tao.player.util.jdbc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tao.player.util.string.Underline2Camel;

public class CreateCode {

	//public static String[] tableName=new String[] {"auth_role","auth_user_role","album","music","singer","favorites",
	//		"music_album_relation","singer_album_relation","singer_music_relation"};
//	public static String[] tableName=new String[] {"album","music","singer"};///
	public static String[] tableName=new String[] {"menu","role_menu_relation"};///

	public static String packagePath = "com.tao.player";
	public static String pathString="C:\\Users\\hongtao\\Downloads\\eclipse-workspace\\player\\src\\main\\java\\com\\tao\\player\\";
	public static void main(String[] args) {

		for (int i = 0; i < tableName.length; i++) {
			createPojo(tableName[i]);
			createDao(tableName[i]);
			createDaoImpl(tableName[i]);
			createService(tableName[i]);
			createServiceImpl(tableName[i]);
			createController(tableName[i]);
		}
	}
	
	private static void createPojo(String tableName) {
		String fileName = Underline2Camel.camel(tableName.toLowerCase());
		fileName = fileName.substring(0,1).toUpperCase()+fileName.substring(1);
		String packagePath=CreateCode.packagePath+".pojo";
		File file = new File(pathString+"\\pojo\\"+fileName+".java");
		if(!file.exists()) {
		}else {
			file.deleteOnExit();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Connection conn = JdbcUtil.getConn();
		ResultSet result = null;
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(file, true));
			out.write("package "+packagePath+";\r\n");
			out.write("import java.io.Serializable;\r\n" + 
					"import com.tao.util.jdbc.JdbcColumeName;\r\n" + 
					"import com.tao.util.jdbc.JdbcTableName;\r\n");
			out.write("@JdbcTableName(name=\""+tableName+"\")\r\n");
			out.write("public class "+fileName+" implements Serializable {\r\n");
			out.write("\tprivate static final long serialVersionUID = 1L;\r\n");
			
			result = conn.getMetaData().getColumns(conn.getCatalog(), "player", tableName, "%");
			System.out.println(result);
			while(result.next()) {
				out.write("\t@JdbcColumeName(name=\""+result.getString("COLUMN_NAME")+"\")\r\n");
				out.write("\tprivate "+getType(result.getString("TYPE_NAME"))+" "+Underline2Camel.camel(result.getString("COLUMN_NAME").toLowerCase())+";\r\n");
			}
			
			out.write("\tpublic "+fileName+"() {\r\n" + 
					"		super();\r\n" + 
					"	}\r\n");
			
			result.absolute(0);
			while(result.next()) {
				String fieldName=Underline2Camel.camel(result.getString("COLUMN_NAME").toLowerCase());
				String typeName=getType(result.getString("TYPE_NAME"));
				out.write("\tpublic "+typeName+" get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1)+"(){return "+fieldName+";}\r\n");
				out.write("\tpublic void set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1)+"("+typeName+" "+fieldName+"){this."+fieldName+"="+fieldName+";}\r\n");
			}
			
			out.write("}");
			out.flush();
			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		} finally {
			if(result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static String getType(String name) {
		if(name == null)return "String";
		
		if(name.indexOf("VARCHAR")>=0) {
			return "String";
		}else if(name.indexOf("INT")>=0) {
			return "Integer";
		}else if(name.indexOf("DATETIME")>=0) {
			return "Date";
		}else {
			return "String";
		}
	}
	
	private static void createDao(String tableName) {

		String typeName = Underline2Camel.camel(tableName.toLowerCase());
		String fileName = "I"+typeName.substring(0,1).toUpperCase()+typeName.substring(1)+"Dao";
		String packagePath = CreateCode.packagePath+".dao";
		File file = new File(pathString+"\\dao\\"+fileName+".java");
		if(!file.exists()) {
		}else {
			file.deleteOnExit();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(file, true));
			String T_typeName = typeName.substring(0,1).toUpperCase()+typeName.substring(1);
			out.write("package "+packagePath+";\r\n");
			out.write("import java.lang.reflect.InvocationTargetException;\r\n" + 
					"import java.sql.SQLException;\r\n" + 
					"import java.util.List;\r\n"+ 
					"import "+CreateCode.packagePath+".pojo."+T_typeName+";\r\n");
			out.write("\r\n");
			out.write("public interface "+fileName+" {\r\n");
			out.write("\r\n");
			
			out.write("	public List<"+T_typeName+"> findAll"+typeName+"s() throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException;\r\n" + 
			"	\r\n" + 
			"	public "+T_typeName+" find"+T_typeName+"ById(int id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException;\r\n" + 
			"	\r\n" + 
			"	public void add"+T_typeName+"("+T_typeName+" "+typeName+") throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;\r\n" + 
			"	\r\n" + 
			"	public void delete"+T_typeName+"("+T_typeName+" "+typeName+") throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SQLException;\r\n" + 
			"	\r\n" + 
			"	public void update"+T_typeName+"("+T_typeName+" "+typeName+") throws SQLException;\r\n" + 
			"	\r\n" + 
			"	public void delete"+T_typeName+"ById(int id) throws SQLException;\r\n");
			out.write("}");
			
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static void createDaoImpl(String tableName) {

		String typeName = Underline2Camel.camel(tableName.toLowerCase());
		String fileName = typeName.substring(0,1).toUpperCase()+typeName.substring(1)+"Dao";
		String packagePath=CreateCode.packagePath+".dao.impl";
		File file = new File(pathString+"\\dao\\impl\\"+fileName+".java");
		if(!file.exists()) {
		}else {
			file.deleteOnExit();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(file, true));
			String T_typeName = typeName.substring(0,1).toUpperCase()+typeName.substring(1);
			out.write("package "+packagePath+";\r\n");
			out.write("import java.lang.reflect.InvocationTargetException;\r\n" + 
					"import java.sql.SQLException;\r\n" + 
					"import java.util.HashMap;\r\n"+  
					"import java.util.List;\r\n"+ 
					"import java.util.Map;\r\n"+ 
					"import "+CreateCode.packagePath+".dao.I"+T_typeName+"Dao;\r\n"+
					"import "+CreateCode.packagePath+".pojo."+T_typeName+";\r\n"+
					"import "+CreateCode.packagePath+".util.BeanUtil;\r\n" + 
					"import "+CreateCode.packagePath+".util.jdbc.JdbcUtil;\r\n");
			out.write("\r\n");
			out.write("public class "+fileName+" implements I"+T_typeName+"Dao{\r\n");
			out.write("\r\n");
			
			out.write("	@Override\r\n"
					+ "public List<"+T_typeName+"> findAll"+typeName+"s() throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException{\r\n"
					+ "List<"+T_typeName+"> list = JdbcUtil.findAll("+T_typeName+".class);return list;}\r\n" + 
			"	\r\n" + 
			"	@Override\r\n"
			+ "public "+T_typeName+" find"+T_typeName+"ById(int id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException{\r\n"
					+ "Map<String, Object> parameters = new HashMap<>();\r\n" + 
					"		parameters.put(\"id\", id);\r\n" + 
					"		List<"+T_typeName+"> list = JdbcUtil.findByMap("+T_typeName+".class, parameters, null);\r\n" + 
					"		if(list.size()>0) {\r\n" + 
					"			return list.get(0);\r\n" + 
					"		}else {\r\n" + 
					"			return null;\r\n" + 
					"		}"
					+ "}\r\n" + 
			"	\r\n" + 
			"	@Override\r\n"
			+ "public void add"+T_typeName+"("+T_typeName+" "+typeName+") throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException\r\n{"
					+ "if("+typeName+".getIsValid()==null) {\r\n" + 
					"			"+typeName+".setIsValid(\"Y\");\r\n" + 
					"		}\r\n" + 
					"		JdbcUtil.add("+typeName+");"
					+ "}\r\n" + 
			"	\r\n" + 
			"	@Override\r\n"
			+ "public void delete"+T_typeName+"("+T_typeName+" "+typeName+") throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SQLException{JdbcUtil.delete("+typeName+");}\r\n" + 
			"	\r\n" + 
			"	@Override\r\n"
			+ "public void update"+T_typeName+"("+T_typeName+" "+typeName+") throws SQLException{\r\n"
					+ "if("+typeName+".getIsValid()==null) {\r\n" + 
					"			"+typeName+".setIsValid(\"Y\");\r\n" + 
					"		}\r\n" + 
					"		Map<String, Object> values = new HashMap<String, Object>();\r\n" + 
					"		BeanUtil.obj2Map(values, "+typeName+");\r\n" + 
					"		values.remove(\"id\");\r\n" + 
					"		Map<String, Object> conditions = new HashMap<>();\r\n" + 
					"		conditions.put(\"id\", "+typeName+".getId());\r\n" + 
					"		JdbcUtil.updateByMap("+typeName+".getClass(), values, conditions);"
					+ "}\r\n" + 
			"	\r\n" + 
			"	@Override\r\n"
			+ "public void delete"+T_typeName+"ById(int id) throws SQLException{\r\n"
					+ "Map<String, Object> conditions = new HashMap<>();\r\n" + 
					"		conditions.put(\"id\", id);\r\n" + 
					"		JdbcUtil.deleteByMap("+T_typeName+".class, conditions);"
					+ "}\r\n");
			out.write("}");
			
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static void createService(String tableName) {

		String typeName = Underline2Camel.camel(tableName.toLowerCase());
		String fileName = "I"+typeName.substring(0,1).toUpperCase()+typeName.substring(1)+"Service";
		String packagePath = CreateCode.packagePath+".service";
		File file = new File(pathString+"\\service\\"+fileName+".java");
		if(!file.exists()) {
		}else {
			file.deleteOnExit();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(file, true));
			String T_typeName = typeName.substring(0,1).toUpperCase()+typeName.substring(1);
			out.write("package "+packagePath+";\r\n");
			out.write("import java.lang.reflect.InvocationTargetException;\r\n" + 
					"import java.sql.SQLException;\r\n" + 
					"import "+CreateCode.packagePath+".pojo."+T_typeName+";\r\n");
			out.write("\r\n");
			out.write("public interface "+fileName+" {\r\n");
			out.write("\r\n");
			
			out.write("	public "+T_typeName+" find"+T_typeName+"ById(int id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException;\r\n" + 
			"	\r\n" + 
			"	public void add"+T_typeName+"("+T_typeName+" "+typeName+") throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;\r\n" + 
			"	\r\n" + 
			"	public void update"+T_typeName+"("+T_typeName+" "+typeName+") throws SQLException;\r\n" + 
			"	\r\n" + 
			"	public void delete"+T_typeName+"ById(int id) throws SQLException;\r\n");
			out.write("}");
			
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static void createServiceImpl(String tableName) {

		String typeName = Underline2Camel.camel(tableName.toLowerCase());
		String fileName = typeName.substring(0,1).toUpperCase()+typeName.substring(1)+"Service";
		String packagePath = CreateCode.packagePath+".service.impl";
		File file = new File(pathString+"\\service\\impl\\"+fileName+".java");
		if(!file.exists()) {
		}else {
			file.deleteOnExit();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(file, true));
			String T_typeName = typeName.substring(0,1).toUpperCase()+typeName.substring(1);
			out.write("package "+packagePath+";\r\n");
			out.write("import java.lang.reflect.InvocationTargetException;\r\n" + 
					"import java.sql.SQLException;\r\n" +
					"import "+CreateCode.packagePath+".dao.I"+T_typeName+"Dao;\r\n"+
					"import "+CreateCode.packagePath+".dao.impl."+T_typeName+"Dao;\r\n"+
					"import "+CreateCode.packagePath+".service.I"+T_typeName+"Service;\r\n"+
					"import "+CreateCode.packagePath+".pojo."+T_typeName+";\r\n");
			out.write("\r\n");
			out.write("public class "+fileName+" implements I"+T_typeName+"Service {\r\n");
			out.write("\r\n");
			out.write("	private I"+T_typeName+"Dao "+typeName+"Dao = new "+T_typeName+"Dao();\r\n");
			out.write("\r\n");
			out.write("	@Override\r\n"
			+ "	public "+T_typeName+" find"+T_typeName+"ById(int id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException{\r\n"
					+ "return "+typeName+"Dao.find"+T_typeName+"ById(id);"
					+ "}\r\n" + 
			"	\r\n" + 
			"	@Override\r\n"
			+ "	public void add"+T_typeName+"("+T_typeName+" "+typeName+") throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException\r\n{"
					+ typeName+"Dao.add"+T_typeName+"("+typeName+");"
					+ "}\r\n" + 
			"	\r\n" + 
			"	@Override\r\n"
			+ "	public void update"+T_typeName+"("+T_typeName+" "+typeName+") throws SQLException{\r\n"
				+ typeName+"Dao.update"+T_typeName+"("+typeName+");"
					+ "}\r\n" + 
			"	\r\n" + 
			"	@Override\r\n"
			+ "	public void delete"+T_typeName+"ById(int id) throws SQLException{\r\n"
				+ typeName+"Dao.delete"+T_typeName+"ById(id);"
					+ "}\r\n");
			out.write("}");
			
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static void createController(String tableName) {
		String typeName = Underline2Camel.camel(tableName.toLowerCase());
		String fileName = typeName.substring(0,1).toUpperCase()+typeName.substring(1)+"Controller";
		String packagePath=CreateCode.packagePath+".controller";
		File file = new File(pathString+"\\controller\\"+fileName+".java");
		if(!file.exists()) {
		}else {
			file.deleteOnExit();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(file, true));
			String T_typeName = typeName.substring(0,1).toUpperCase()+typeName.substring(1);
			out.write("package "+packagePath+";\r\n");
			out.write("import java.lang.reflect.InvocationTargetException;\r\n" + 
					"import java.lang.reflect.Method;\r\n"+  
					"import java.sql.SQLException;\r\n" + 
					"import java.io.IOException;\r\n"+ 
					"import javax.servlet.ServletException;\r\n" + 
					"import javax.servlet.annotation.WebServlet;\r\n" + 
					"import javax.servlet.http.HttpServlet;\r\n" + 
					"import javax.servlet.http.HttpServletRequest;\r\n" + 
					"import javax.servlet.http.HttpServletResponse;\r\n"+
					"import com.alibaba.fastjson.JSONObject;\r\n"+
					"import "+CreateCode.packagePath+".pojo."+T_typeName+";\r\n"+
					"import "+CreateCode.packagePath+".service.I"+T_typeName+"Service;\r\n"+
					"import "+CreateCode.packagePath+".service.impl."+T_typeName+"Service;\r\n"+
					"import "+CreateCode.packagePath+".util.proxy.ProxyService;\r\n");
			out.write("\r\n");
			out.write("@WebServlet(\"/"+T_typeName+"Controller\")\r\n");
			out.write("public class "+fileName+" extends HttpServlet{\r\n");
			out.write("private static final long serialVersionUID=1L;\r\n");
			out.write("\r\n");
			out.write("	private I"+T_typeName+"Service "+typeName+"Service = (I"+T_typeName+"Service)new ProxyService().setTarget(new "+T_typeName+"Service()).getProxy();\r\n");
			out.write("\r\n");
			out.write("	@Override\r\n" + 
					"	protected void service(HttpServletRequest request, HttpServletResponse response)\r\n" + 
					"			throws ServletException, IOException {\r\n" + 
					"		response.setContentType(\"application/json;charset=UTF-8\");\r\n" + 
					"		String methodname = request.getParameter(\"method\");\r\n" + 
					"		try {\r\n" + 
					"			Method method = getClass().getDeclaredMethod(methodname, HttpServletRequest.class,\r\n" + 
					"					HttpServletResponse.class);\r\n" + 
					"			Object result = method.invoke(this, request, response);// 调用各自的方法\r\n" + 
					"			response.getWriter().println(result);\r\n" + 
					"			response.getWriter().close();\r\n" + 
					"		} catch (Exception e) {\r\n" + 
					"			e.printStackTrace();\r\n" + 
					"		}\r\n" + 
					"	}\r\n" + 
			"	\r\n" + 
			"	protected JSONObject find"+T_typeName+"ById(HttpServletRequest request, HttpServletResponse response) {\r\n" + 
			"		JSONObject result = new JSONObject();\r\n" + 
			"		try {\r\n" + 
			"			Integer id = Integer.parseInt(request.getParameter(\"id\"));\r\n" + 
			"			System.out.println(\"id==\" + id);\r\n" + 
			"			"+T_typeName+" "+typeName+" = "+typeName+"Service.find"+T_typeName+"ById(id);\r\n" + 
			"			\r\n" + 
			"			result.put(\"data\","+typeName+");\r\n" + 
			"			result.put(\"code\", \"02\");\r\n" + 
			"			System.out.println(result);\r\n" + 
			"		} catch (InstantiationException | IllegalAccessException | NoSuchFieldException | SecurityException\r\n" + 
			"				| IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SQLException | IOException e) {\r\n" + 
			"			e.printStackTrace();\r\n" + 
			"		}\r\n" + 
			"		return result;\r\n" + 
			"	}\r\n" + 
			"	\r\n" + 
			"	protected JSONObject add"+T_typeName+"(HttpServletRequest request, HttpServletResponse response) {\r\n" + 
			"		JSONObject result = new JSONObject();\r\n" + 
			"		Integer age = Integer.parseInt(request.getParameter(\"age\"));\r\n" + 
			"		String username = request.getParameter(\"username\");\r\n" + 
			"		String location = request.getParameter(\"location\");\r\n" + 
			"		"+T_typeName+" "+typeName+" = new "+T_typeName+"(username, \"123456\", age, location, null);\r\n" + 
			"		try {\r\n" + 
			"			"+typeName+"Service.add"+T_typeName+"("+typeName+");\r\n" + 
			"\r\n" + 
			"			result.put(\"code\", \"01\");\r\n" + 
			"		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException\r\n" + 
			"				| NoSuchMethodException | SecurityException |SQLException | IOException e) {\r\n" + 
			"			e.printStackTrace();\r\n" + 
			"		}\r\n" + 
			"		return result;\r\n" + 
			"	}\r\n" + 
			"	\r\n" + 
			"	protected JSONObject delete"+T_typeName+"ById(HttpServletRequest request, HttpServletResponse response) {\r\n" + 
			"		JSONObject result = new JSONObject();\r\n" + 
			"		try {\r\n" + 
			"			int id = Integer.parseInt(request.getParameter(\"id\"));\r\n" + 
			"			System.out.println(\"id==\" + id);\r\n" + 
			"			"+typeName+"Service.delete"+T_typeName+"ById(id);\r\n" + 
			"			\r\n" + 
			"			result.put(\"code\", \"02\");\r\n" + 
			"			System.out.println(result);\r\n" + 
			"		} catch (SecurityException | IllegalArgumentException | SQLException | IOException e) {\r\n" + 
			"			e.printStackTrace();\r\n" + 
			"		}\r\n" + 
			"		return result;\r\n" + 
			"	}\r\n");
			out.write("}");
			
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
