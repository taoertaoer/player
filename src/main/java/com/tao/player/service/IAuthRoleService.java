package com.tao.player.service;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import com.tao.player.pojo.AuthRole;

public interface IAuthRoleService {

	public AuthRole findAuthRoleById(int id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException;
	
	public AuthRole findAuthRoleByAuthUserId(int id);
	
	public void addAuthRole(AuthRole authRole) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	
	public void updateAuthRole(AuthRole authRole) throws SQLException;
	
	public void deleteAuthRoleById(int id) throws SQLException;
}