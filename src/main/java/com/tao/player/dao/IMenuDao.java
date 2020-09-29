package com.tao.player.dao;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tao.player.pojo.Menu;

public interface IMenuDao {

	public List<Menu> findAllMenus() throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException;
	
	public List<Menu> findMenusByConditions(Map<String, Object> conditions);
	
	public List<Menu> findMenuByRoleId(Integer id);
	
	public Menu findMenuById(int id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException;
	
	public void addMenu(Menu menu) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	
	public void deleteMenu(Menu menu) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SQLException;
	
	public void updateMenu(Menu menu) throws SQLException;
	
	public void deleteMenuById(int id) throws SQLException;
}