package com.tao.player.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.tao.player.dao.IUserDao;
import com.tao.player.pojo.AuthUser;
import com.tao.player.util.BeanUtil;
import com.tao.player.util.jdbc.JdbcUtil;
import com.tao.player.util.page.Page;
import com.tao.player.util.string.Underline2Camel;

public class UserDao implements IUserDao {

	@Override
	public String getUser() {
		return "dddxx";
	}

	@Override
	public List<AuthUser> findAllAuthUsers() throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException{
		
		//CriteriaQuery<AuthUser> criteriaQuery = getSession().getCriteriaBuilder().createQuery(AuthUser.class);
		//criteriaQuery.from(AuthUser.class);
		//return getSession().createQuery(criteriaQuery).getResultList();
		
		List<AuthUser> list = JdbcUtil.findAll(AuthUser.class);
		return list;
	}
	
	@Override
	public List<AuthUser> findAuthUsersByConditions(Map<String, Object> conditions) {
		conditions.remove("serialVersionUID");
		String sql = " select * from auth_user where 1 = 1 ";
		List<Object> parameters = new ArrayList<>();
		Map<String, String> columns = JdbcUtil.getClumnName(AuthUser.class); 
		for (Iterator<String> iterator = conditions.keySet().iterator(); iterator.hasNext();) {
			String field = iterator.next();
			if(conditions.get(field) != null) {
				sql = sql + " and "+columns.get(field)+" = ? ";
				parameters.add(conditions.get(field));
			}
		}
		sql = sql + " order by create_time desc ";
		System.out.println("findAuthUsersByConditions sql=="+sql);
		List<Map<String, Object>> list = JdbcUtil.get(sql, parameters);
		List<AuthUser> result = new ArrayList<>();
		for (Iterator<Map<String, Object>> iterator = list.iterator(); iterator.hasNext();) {
			Map<String, Object> map = iterator.next();
			Map<String, Object> m = new HashMap<>();
			for (Iterator<String> iterator2 = map.keySet().iterator(); iterator2.hasNext();) {
				String column = iterator2.next();
				m.put(Underline2Camel.camel(column.toLowerCase()), map.get(column));
			}
			result.add(BeanUtil.map2Obj(new AuthUser(), m));
		}
		
		return result;
	}
	
	@Override
	public List<AuthUser> findAuthUsersPagingByConditions(Map<String, Object> conditions, Page page) {
		conditions.remove("serialVersionUID");
		String sql = " select * from auth_user where 1 = 1 ";
		List<Object> parameters = new ArrayList<>();
		Map<String, String> columns = JdbcUtil.getClumnName(AuthUser.class); 
		for (Iterator<String> iterator = conditions.keySet().iterator(); iterator.hasNext();) {
			String field = iterator.next();
			if(conditions.get(field) != null) {
				sql = sql + " and "+columns.get(field)+" = ? ";
				parameters.add(conditions.get(field));
			}
		}
		sql = sql + " order by create_time desc ";
		System.out.println("findAuthUsersPagingByConditions sql=="+sql);
		Map<String, Integer> pageMap = new HashMap<>();
		pageMap.put("start", page.getPageIndex());
		pageMap.put("limit", page.getPageSize());
		
		List<Map<String, Object>> list = JdbcUtil.getListWithMysqlPaging(sql, parameters, pageMap);
		List<AuthUser> result = new ArrayList<>();
		for (Iterator<Map<String, Object>> iterator = list.iterator(); iterator.hasNext();) {
			Map<String, Object> map = iterator.next();
			Map<String, Object> m = new HashMap<>();
			for (Iterator<String> iterator2 = map.keySet().iterator(); iterator2.hasNext();) {
				String column = iterator2.next();
				m.put(Underline2Camel.camel(column.toLowerCase()), map.get(column));
			}
			result.add(BeanUtil.map2Obj(new AuthUser(), m));
		}
		
		return result;
	}
	
	@Override
	public Integer findAuthUsersCountByConditions(Map<String, Object> conditions) {
		conditions.remove("serialVersionUID");
		String sql = " select count(1) count from auth_user where 1 = 1 ";
		List<Object> parameters = new ArrayList<>();
		Map<String, String> columns = JdbcUtil.getClumnName(AuthUser.class); 
		for (Iterator<String> iterator = conditions.keySet().iterator(); iterator.hasNext();) {
			String field = iterator.next();
			if(conditions.get(field) != null) {
				sql = sql + " and "+columns.get(field)+" = ? ";
				parameters.add(conditions.get(field));
			}
		}
		sql = sql + " order by create_time desc ";
		System.out.println("findAuthUsersCountByConditions sql=="+sql);
		List<Map<String, Object>> list = JdbcUtil.get(sql, parameters);
		return (Integer) list.get(0).get("count");
	}

	@Override
	public AuthUser findAuthUserById(int id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("id", id);
		List<AuthUser> list = JdbcUtil.findByMap(AuthUser.class, parameters, null);
		if(list.size()>0) {
			return list.get(0);
		}else {
			return null;
		}
	}

	@Override
	public void addAuthUser(AuthUser authUser) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		if(authUser.getProfileFilePath()==null) {
			authUser.setProfileFilePath("static/profile/profile.png");
		}
		JdbcUtil.add(authUser);
	}

	@Override
	public void deleteAuthUser(AuthUser authUser) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SQLException {
		JdbcUtil.delete(authUser);
	}

	@Override
	public void updateAuthUser(AuthUser authUser) throws SQLException {
		if(authUser.getProfileFilePath()==null) {
			authUser.setProfileFilePath("static/profile/profile.png");
		}
		Map<String, Object> values = new HashMap<String, Object>();
		BeanUtil.obj2Map(values, authUser);
		values.remove("id");
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("id", authUser.getId());
		JdbcUtil.updateByMap(authUser.getClass(), values, conditions);
	}

	@Override
	public void deleteAuthUserById(int id) throws SQLException{
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("id", id);
		JdbcUtil.deleteByMap(AuthUser.class, conditions);
	}
	
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < 4; i++) {				
			list.add("?");
		}
		System.out.println(StringUtils.join(list, ","));
		
		AuthUser authUser = new AuthUser("21", null,null, null, null, null, null, null);
		System.out.println(BeanUtil.obj2Map(new HashMap<>(), authUser));
		
		UserDao user = new UserDao();
		Map<String, Object> map =BeanUtil.obj2Map(new HashMap<>(), authUser); 
		map.remove("serialVersionUID");
		map.remove("serialVersionUID");
		List<AuthUser> r = user.findAuthUsersByConditions(map);
		System.out.println(r);
	}
}
