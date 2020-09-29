package com.tao.player.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tao.player.dao.IMenuDao;
import com.tao.player.pojo.AuthUser;
import com.tao.player.pojo.Menu;
import com.tao.player.util.BeanUtil;
import com.tao.player.util.jdbc.JdbcUtil;
import com.tao.player.util.string.Underline2Camel;

public class MenuDao implements IMenuDao {

	public static void main(String[] args) {
		MenuDao menuDao = new MenuDao();
		List<Menu> list=null;
		try {
			list = menuDao.findAllMenus();
		} catch (InstantiationException | IllegalAccessException | NoSuchFieldException | SecurityException
				| IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SQLException e) {
			e.printStackTrace();
		}
		System.out.println(list);
	}
	@Override
	public List<Menu> findAllMenus()
			throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException,
			SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		List<Menu> list = JdbcUtil.findAll(Menu.class);
		return list;
	}
	
	@Override
	public List<Menu> findMenusByConditions(Map<String, Object> conditions) {
		conditions.remove("serialVersionUID");
		String sql = " select * from menu where 1 = 1 ";
		List<Object> parameters = new ArrayList<>();
		Map<String, String> columns = JdbcUtil.getClumnName(Menu.class); 
		for (Iterator<String> iterator = conditions.keySet().iterator(); iterator.hasNext();) {
			String field = iterator.next();
			if(conditions.get(field) != null) {
				sql = sql + " and "+columns.get(field)+" = ? ";
				parameters.add(conditions.get(field));
			}
		}
		System.out.println("findAuthUsersByConditions sql=="+sql);
		List<Map<String, Object>> list = JdbcUtil.get(sql, parameters);
		List<Menu> result = new ArrayList<>();
		for (Iterator<Map<String, Object>> iterator = list.iterator(); iterator.hasNext();) {
			Map<String, Object> map = iterator.next();
			Map<String, Object> m = new HashMap<>();
			for (Iterator<String> iterator2 = map.keySet().iterator(); iterator2.hasNext();) {
				String column = iterator2.next();
				m.put(Underline2Camel.camel(column.toLowerCase()), map.get(column));
			}
			result.add(BeanUtil.map2Obj(new Menu(), m));
		}
		
		return result;
	}

	@Override
	public List<Menu> findMenuByRoleId(Integer id){
		String sql = "select m.* from menu m, role_menu_relation rm where m.id=rm.menu_id and rm.role_id=?";
		List<Object> parameters = new ArrayList<>();
		parameters.add(id);
		List<Map<String, Object>> list = JdbcUtil.get(sql, parameters);
		List<Menu> result = new ArrayList<>();
		for (Iterator<Map<String, Object>> iterator = list.iterator(); iterator.hasNext();) {
			Map<String, Object> map = iterator.next();
			Map<String, Object> m = new HashMap<>();
			for (Iterator<String> iterator2 = map.keySet().iterator(); iterator2.hasNext();) {
				String column = iterator2.next();
				m.put(Underline2Camel.camel(column.toLowerCase()), map.get(column));
			}
			result.add(BeanUtil.map2Obj(new Menu(), m));
		}
		return result;
	}

	@Override
	public Menu findMenuById(int id)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("id", id);
		List<Menu> list = JdbcUtil.findByMap(Menu.class, parameters, null);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void addMenu(Menu menu) throws SQLException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		if (menu.getIsValid() == null) {
			menu.setIsValid("Y");
		}
		JdbcUtil.add(menu);
	}

	@Override
	public void deleteMenu(Menu menu) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, SQLException {
		JdbcUtil.delete(menu);
	}

	@Override
	public void updateMenu(Menu menu) throws SQLException {
		if (menu.getIsValid() == null) {
			menu.setIsValid("Y");
		}
		Map<String, Object> values = new HashMap<String, Object>();
		BeanUtil.obj2Map(values, menu);
		values.remove("id");
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("id", menu.getId());
		JdbcUtil.updateByMap(menu.getClass(), values, conditions);
	}

	@Override
	public void deleteMenuById(int id) throws SQLException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("id", id);
		JdbcUtil.deleteByMap(Menu.class, conditions);
	}
}