package com.tao.player.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tao.player.pojo.AuthUser;
import com.tao.player.util.page.Page;

public interface IUserDao {
	
	public String getUser();
	
	public List<AuthUser> findAllAuthUsers() throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException;
	
	public AuthUser findAuthUserById(int id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException;
	
	public List<AuthUser> findAuthUsersByConditions(Map<String, Object> conditions);
	
	public List<AuthUser> findAuthUsersPagingByConditions(Map<String, Object> conditions, Page page);
	
	public Integer findAuthUsersCountByConditions(Map<String, Object> conditions);
	
	public void addAuthUser(AuthUser authUser) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	
	public void deleteAuthUser(AuthUser authUser) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SQLException;
	
	public void updateAuthUser(AuthUser authUser) throws SQLException;
	
	public void deleteAuthUserById(int id) throws SQLException;
}
