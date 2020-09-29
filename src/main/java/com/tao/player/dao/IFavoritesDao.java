package com.tao.player.dao;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import com.tao.player.pojo.Favorites;

public interface IFavoritesDao {

	public List<Favorites> findAllfavoritess() throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException;
	
	public Favorites findFavoritesById(int id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException;
	
	public void addFavorites(Favorites favorites) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	
	public void deleteFavorites(Favorites favorites) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SQLException;
	
	public void updateFavorites(Favorites favorites) throws SQLException;
	
	public void deleteFavoritesById(int id) throws SQLException;
}