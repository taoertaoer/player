package com.tao.player.service;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import com.tao.player.pojo.RoleMenuRelation;

public interface IRoleMenuRelationService {

	public RoleMenuRelation findRoleMenuRelationById(int id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException;
	
	public void addRoleMenuRelation(RoleMenuRelation roleMenuRelation) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	
	public void updateRoleMenuRelation(RoleMenuRelation roleMenuRelation) throws SQLException;
	
	public void deleteRoleMenuRelationById(int id) throws SQLException;
}