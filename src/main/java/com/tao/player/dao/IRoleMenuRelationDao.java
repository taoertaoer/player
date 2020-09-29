package com.tao.player.dao;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import com.tao.player.pojo.RoleMenuRelation;

public interface IRoleMenuRelationDao {

	public List<RoleMenuRelation> findAllroleMenuRelations() throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException;
	
	public RoleMenuRelation findRoleMenuRelationById(int id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException;
	
	public void addRoleMenuRelation(RoleMenuRelation roleMenuRelation) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	
	public void deleteRoleMenuRelation(RoleMenuRelation roleMenuRelation) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SQLException;
	
	public void updateRoleMenuRelation(RoleMenuRelation roleMenuRelation) throws SQLException;
	
	public void deleteRoleMenuRelationById(int id) throws SQLException;
}