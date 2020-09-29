package com.tao.player.service;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import com.tao.player.pojo.AuthUserRole;

public interface IAuthUserRoleService {

	public AuthUserRole findAuthUserRoleById(int id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException;
	
	public void addAuthUserRole(AuthUserRole authUserRole) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	
	public void updateAuthUserRole(AuthUserRole authUserRole) throws SQLException;
	
	public void deleteAuthUserRoleById(int id) throws SQLException;
}