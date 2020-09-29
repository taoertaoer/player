package com.tao.player.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tao.player.dao.IAuthUserRoleDao;
import com.tao.player.pojo.AuthUserRole;
import com.tao.player.util.BeanUtil;
import com.tao.player.util.jdbc.JdbcUtil;

public class AuthUserRoleDao implements IAuthUserRoleDao {

	@Override
	public List<AuthUserRole> findAllauthUserRoles()
			throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException,
			SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		List<AuthUserRole> list = JdbcUtil.findAll(AuthUserRole.class);
		return list;
	}

	@Override
	public AuthUserRole findAuthUserRoleById(int id)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("id", id);
		List<AuthUserRole> list = JdbcUtil.findByMap(AuthUserRole.class, parameters, null);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void addAuthUserRole(AuthUserRole authUserRole) throws SQLException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		JdbcUtil.add(authUserRole);
	}

	@Override
	public void deleteAuthUserRole(AuthUserRole authUserRole) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, SQLException {
		JdbcUtil.delete(authUserRole);
	}

	@Override
	public void updateAuthUserRole(AuthUserRole authUserRole) throws SQLException {
		Map<String, Object> values = new HashMap<String, Object>();
		BeanUtil.obj2Map(values, authUserRole);
		values.remove("id");
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("id", authUserRole.getId());
		JdbcUtil.updateByMap(authUserRole.getClass(), values, conditions);
	}

	@Override
	public void deleteAuthUserRoleById(int id) throws SQLException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("id", id);
		JdbcUtil.deleteByMap(AuthUserRole.class, conditions);
	}
}