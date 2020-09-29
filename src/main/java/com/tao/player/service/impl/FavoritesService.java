package com.tao.player.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import com.tao.player.dao.IFavoritesDao;
import com.tao.player.dao.impl.FavoritesDao;
import com.tao.player.pojo.Favorites;
import com.tao.player.service.IFavoritesService;

public class FavoritesService implements IFavoritesService {

	private IFavoritesDao favoritesDao = new FavoritesDao();

	@Override
	public Favorites findFavoritesById(int id)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException {
		return favoritesDao.findFavoritesById(id);
	}

	@Override
	public void addFavorites(Favorites favorites) throws SQLException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		favoritesDao.addFavorites(favorites);
	}

	@Override
	public void updateFavorites(Favorites favorites) throws SQLException {
		favoritesDao.updateFavorites(favorites);
	}

	@Override
	public void deleteFavoritesById(int id) throws SQLException {
		favoritesDao.deleteFavoritesById(id);
	}
}