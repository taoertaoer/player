package com.tao.player.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import com.tao.player.pojo.AuthRole;

public interface IAuthRoleDao {

	public List<AuthRole> findAllauthRoles()
			throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException,
			SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException;

	public AuthRole findAuthRoleById(int id)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException;
	
	public AuthRole findAuthRoleByAuthUserId(int id);

	public void addAuthRole(AuthRole authRole) throws SQLException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException;

	public void deleteAuthRole(AuthRole authRole) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, SQLException;

	public void updateAuthRole(AuthRole authRole) throws SQLException;

	public void deleteAuthRoleById(int id) throws SQLException;
}