package com.tao.player.dao;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import com.tao.player.pojo.AuthUserRole;

public interface IAuthUserRoleDao {

	public List<AuthUserRole> findAllauthUserRoles() throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException;
	
	public AuthUserRole findAuthUserRoleById(int id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException;
	
	public void addAuthUserRole(AuthUserRole authUserRole) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	
	public void deleteAuthUserRole(AuthUserRole authUserRole) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SQLException;
	
	public void updateAuthUserRole(AuthUserRole authUserRole) throws SQLException;
	
	public void deleteAuthUserRoleById(int id) throws SQLException;
}