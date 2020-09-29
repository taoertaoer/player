package com.tao.player.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tao.player.dao.IAuthRoleDao;
import com.tao.player.pojo.AuthRole;
import com.tao.player.pojo.Menu;
import com.tao.player.util.BeanUtil;
import com.tao.player.util.jdbc.JdbcUtil;
import com.tao.player.util.string.Underline2Camel;

public class AuthRoleDao implements IAuthRoleDao {

	@Override
	public List<AuthRole> findAllauthRoles()
			throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException,
			SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		List<AuthRole> list = JdbcUtil.findAll(AuthRole.class);
		return list;
	}

	@Override
	public AuthRole findAuthRoleById(int id)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("id", id);
		List<AuthRole> list = JdbcUtil.findByMap(AuthRole.class, parameters, null);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	@Override
	public AuthRole findAuthRoleByAuthUserId(int id){
		String sql ="select r.* from auth_role r,auth_user_role ar where r.id=ar.role_id and ar.user_id=?";
		List<Object> parameters = new ArrayList<>();
		parameters.add(id);
		List<Map<String, Object>> list = JdbcUtil.get(sql, parameters);
		if (list.size() > 0) {
			Map<String, Object> map = list.get(0);
			Map<String, Object> a = new HashMap<>();
			for (Iterator<String> iterator2 = map.keySet().iterator(); iterator2.hasNext();) {
				String column = iterator2.next();
				a.put(Underline2Camel.camel(column.toLowerCase()), map.get(column));
			}
			return BeanUtil.map2Obj(new AuthRole(), a);
		} else {
			return null;
		}
	}

	@Override
	public void addAuthRole(AuthRole authRole) throws SQLException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		if (authRole.getIsValid() == null) {
			authRole.setIsValid("Y");
		}
		JdbcUtil.add(authRole);
	}

	@Override
	public void deleteAuthRole(AuthRole authRole) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, SQLException {
		JdbcUtil.delete(authRole);
	}

	@Override
	public void updateAuthRole(AuthRole authRole) throws SQLException {
		if (authRole.getIsValid() == null) {
			authRole.setIsValid("Y");
		}
		Map<String, Object> values = new HashMap<String, Object>();
		BeanUtil.obj2Map(values, authRole);
		values.remove("id");
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("id", authRole.getId());
		JdbcUtil.updateByMap(authRole.getClass(), values, conditions);
	}

	@Override
	public void deleteAuthRoleById(int id) throws SQLException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("id", id);
		JdbcUtil.deleteByMap(AuthRole.class, conditions);
	}
}