package com.tao.player.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tao.player.dao.IUserDao;
import com.tao.player.dao.impl.UserDao;
import com.tao.player.pojo.AuthUser;
import com.tao.player.service.IUserService;
import com.tao.player.util.BeanUtil;
import com.tao.player.util.page.Page;

//@Service
public class UserService implements IUserService {

	@Autowired
	private IUserDao userDao = new UserDao();

	@Override
	public String getUser() {
		return userDao.getUser();
	}

	@Override
	// @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
	public AuthUser findAuthUserById(int id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException {
		return userDao.findAuthUserById(id);
	}

	@Override
	// @Transactional(propagation = Propagation.REQUIRED,rollbackFor =
	// Exception.class)
	public List<AuthUser> findAllAuthUsers() throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		return userDao.findAllAuthUsers();
	}
	
	public List<AuthUser> findAuthUsers(AuthUser authUser){
		return userDao.findAuthUsersByConditions(BeanUtil.obj2Map(new HashMap<>(), authUser));
	}
	
	public List<AuthUser> findAuthUsersPaging(AuthUser authUser, Page page){
		return userDao.findAuthUsersPagingByConditions(BeanUtil.obj2Map(new HashMap<>(), authUser), page);
	}
	
	public Integer findAuthUsersCount(AuthUser authUser){
		return userDao.findAuthUsersCountByConditions(BeanUtil.obj2Map(new HashMap<>(), authUser));
	}

	@Override
	public void addAuthUser(AuthUser authUser) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SQLException{
		userDao.addAuthUser(authUser);
	}

	@Override
	public void updateAuthUser(AuthUser authUser) throws SQLException {
		userDao.updateAuthUser(authUser);
	}

	@Override
	public void deleteAuthUserById(int id) throws SQLException {
		userDao.deleteAuthUserById(id);
	}
}
