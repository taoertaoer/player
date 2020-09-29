package com.tao.player.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tao.player.dao.IRoleMenuRelationDao;
import com.tao.player.pojo.RoleMenuRelation;
import com.tao.player.util.BeanUtil;
import com.tao.player.util.jdbc.JdbcUtil;

public class RoleMenuRelationDao implements IRoleMenuRelationDao {

	@Override
	public List<RoleMenuRelation> findAllroleMenuRelations()
			throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException,
			SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		List<RoleMenuRelation> list = JdbcUtil.findAll(RoleMenuRelation.class);
		return list;
	}

	@Override
	public RoleMenuRelation findRoleMenuRelationById(int id)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("id", id);
		List<RoleMenuRelation> list = JdbcUtil.findByMap(RoleMenuRelation.class, parameters, null);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void addRoleMenuRelation(RoleMenuRelation roleMenuRelation) throws SQLException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		JdbcUtil.add(roleMenuRelation);
	}

	@Override
	public void deleteRoleMenuRelation(RoleMenuRelation roleMenuRelation)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException, SQLException {
		JdbcUtil.delete(roleMenuRelation);
	}

	@Override
	public void updateRoleMenuRelation(RoleMenuRelation roleMenuRelation) throws SQLException {
		Map<String, Object> values = new HashMap<String, Object>();
		BeanUtil.obj2Map(values, roleMenuRelation);
		values.remove("id");
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("id", roleMenuRelation.getId());
		JdbcUtil.updateByMap(roleMenuRelation.getClass(), values, conditions);
	}

	@Override
	public void deleteRoleMenuRelationById(int id) throws SQLException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("id", id);
		JdbcUtil.deleteByMap(RoleMenuRelation.class, conditions);
	}
}