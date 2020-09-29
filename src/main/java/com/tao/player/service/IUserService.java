package com.tao.player.service;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import com.tao.player.pojo.AuthUser;
import com.tao.player.util.page.Page;

public interface IUserService {

	public String getUser();
	
	public List<AuthUser> findAllAuthUsers() throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException;
	
	public List<AuthUser> findAuthUsers(AuthUser authUser);
	
	public List<AuthUser> findAuthUsersPaging(AuthUser authUser, Page page);
	
	public Integer findAuthUsersCount(AuthUser authUser);
	
	public AuthUser findAuthUserById(int id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException;
	
	public void addAuthUser(AuthUser authUser) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SQLException ;
	
	public void updateAuthUser(AuthUser authUser) throws SQLException;
	
	//public void updateAuthUser()
	
	public void deleteAuthUserById(int id) throws SQLException;

}
