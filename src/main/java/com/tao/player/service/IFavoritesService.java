package com.tao.player.service;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import com.tao.player.pojo.Favorites;

public interface IFavoritesService {

	public Favorites findFavoritesById(int id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException;
	
	public void addFavorites(Favorites favorites) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	
	public void updateFavorites(Favorites favorites) throws SQLException;
	
	public void deleteFavoritesById(int id) throws SQLException;
}