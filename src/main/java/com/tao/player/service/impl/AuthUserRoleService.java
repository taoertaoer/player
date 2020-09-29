package com.tao.player.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import com.tao.player.dao.IAuthUserRoleDao;
import com.tao.player.dao.impl.AuthUserRoleDao;
import com.tao.player.pojo.AuthUserRole;
import com.tao.player.service.IAuthUserRoleService;

public class AuthUserRoleService implements IAuthUserRoleService {

	private IAuthUserRoleDao authUserRoleDao = new AuthUserRoleDao();

	@Override
	public AuthUserRole findAuthUserRoleById(int id)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException {
		return authUserRoleDao.findAuthUserRoleById(id);
	}

	@Override
	public void addAuthUserRole(AuthUserRole authUserRole) throws SQLException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		authUserRoleDao.addAuthUserRole(authUserRole);
	}

	@Override
	public void updateAuthUserRole(AuthUserRole authUserRole) throws SQLException {
		authUserRoleDao.updateAuthUserRole(authUserRole);
	}

	@Override
	public void deleteAuthUserRoleById(int id) throws SQLException {
		authUserRoleDao.deleteAuthUserRoleById(id);
	}
}