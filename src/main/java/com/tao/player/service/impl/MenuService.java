package com.tao.player.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.tao.player.dao.IMenuDao;
import com.tao.player.dao.impl.MenuDao;
import com.tao.player.pojo.Menu;
import com.tao.player.service.IMenuService;
import com.tao.player.util.BeanUtil;

public class MenuService implements IMenuService {

	private IMenuDao menuDao = new MenuDao();

	@Override
	public List<Menu> findAllMenus() throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException{
		return menuDao.findAllMenus();
	}
	
	@Override
	public List<Menu> findMenus(Menu menu) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException{
		return menuDao.findMenusByConditions(BeanUtil.obj2Map(new HashMap<>(), menu));
	}
	
	@Override
	public Menu findMenuById(int id)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException {
		return menuDao.findMenuById(id);
	}
	
	@Override
	public List<Menu> findMenuByRoleId(Integer id){
		return menuDao.findMenuByRoleId(id);
	}

	@Override
	public void addMenu(Menu menu) throws SQLException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		menuDao.addMenu(menu);
	}

	@Override
	public void updateMenu(Menu menu) throws SQLException {
		menuDao.updateMenu(menu);
	}

	@Override
	public void deleteMenuById(int id) throws SQLException {
		menuDao.deleteMenuById(id);
	}
}