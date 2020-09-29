package com.tao.player.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import com.tao.player.dao.IRoleMenuRelationDao;
import com.tao.player.dao.impl.RoleMenuRelationDao;
import com.tao.player.pojo.RoleMenuRelation;
import com.tao.player.service.IRoleMenuRelationService;

public class RoleMenuRelationService implements IRoleMenuRelationService {

	private IRoleMenuRelationDao roleMenuRelationDao = new RoleMenuRelationDao();

	@Override
	public RoleMenuRelation findRoleMenuRelationById(int id)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException {
		return roleMenuRelationDao.findRoleMenuRelationById(id);
	}

	@Override
	public void addRoleMenuRelation(RoleMenuRelation roleMenuRelation) throws SQLException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		roleMenuRelationDao.addRoleMenuRelation(roleMenuRelation);
	}

	@Override
	public void updateRoleMenuRelation(RoleMenuRelation roleMenuRelation) throws SQLException {
		roleMenuRelationDao.updateRoleMenuRelation(roleMenuRelation);
	}

	@Override
	public void deleteRoleMenuRelationById(int id) throws SQLException {
		roleMenuRelationDao.deleteRoleMenuRelationById(id);
	}
}