package com.tao.player.service;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import com.tao.player.pojo.Menu;

public interface IMenuService {
	
	public List<Menu> findAllMenus() throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException; 

	public List<Menu> findMenus(Menu menu) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException; 
	
	public List<Menu> findMenuByRoleId(Integer id);

	public Menu findMenuById(int id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException;
	
	public void addMenu(Menu menu) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	
	public void updateMenu(Menu menu) throws SQLException;
	
	public void deleteMenuById(int id) throws SQLException;

}