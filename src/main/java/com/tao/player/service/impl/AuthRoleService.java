package com.tao.player.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import com.tao.player.dao.IAuthRoleDao;
import com.tao.player.dao.impl.AuthRoleDao;
import com.tao.player.pojo.AuthRole;
import com.tao.player.service.IAuthRoleService;

public class AuthRoleService implements IAuthRoleService {

	private IAuthRoleDao authRoleDao = new AuthRoleDao();

	@Override
	public AuthRole findAuthRoleById(int id)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException {
		return authRoleDao.findAuthRoleById(id);
	}
	
	@Override
	public AuthRole findAuthRoleByAuthUserId(int id){
		return authRoleDao.findAuthRoleByAuthUserId(id);
	}

	@Override
	public void addAuthRole(AuthRole authRole) throws SQLException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		authRoleDao.addAuthRole(authRole);
	}

	@Override
	public void updateAuthRole(AuthRole authRole) throws SQLException {
		authRoleDao.updateAuthRole(authRole);
	}

	@Override
	public void deleteAuthRoleById(int id) throws SQLException {
		authRoleDao.deleteAuthRoleById(id);
	}
}