package com.tao.player.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tao.player.dao.IFavoritesDao;
import com.tao.player.pojo.Favorites;
import com.tao.player.util.BeanUtil;
import com.tao.player.util.jdbc.JdbcUtil;

public class FavoritesDao implements IFavoritesDao {

	@Override
	public List<Favorites> findAllfavoritess()
			throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException,
			SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		List<Favorites> list = JdbcUtil.findAll(Favorites.class);
		return list;
	}

	@Override
	public Favorites findFavoritesById(int id)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("id", id);
		List<Favorites> list = JdbcUtil.findByMap(Favorites.class, parameters, null);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void addFavorites(Favorites favorites) throws SQLException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		JdbcUtil.add(favorites);
	}

	@Override
	public void deleteFavorites(Favorites favorites) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, SQLException {
		JdbcUtil.delete(favorites);
	}

	@Override
	public void updateFavorites(Favorites favorites) throws SQLException {
		Map<String, Object> values = new HashMap<String, Object>();
		BeanUtil.obj2Map(values, favorites);
		values.remove("id");
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("id", favorites.getId());
		JdbcUtil.updateByMap(favorites.getClass(), values, conditions);
	}

	@Override
	public void deleteFavoritesById(int id) throws SQLException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("id", id);
		JdbcUtil.deleteByMap(Favorites.class, conditions);
	}
}