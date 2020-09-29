package com.tao.player.dao;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import com.tao.player.pojo.Album;

public interface IAlbumDao {

	public List<Album> findAllalbums() throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException;
	
	public Album findAlbumById(int id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException;
	
	public void addAlbum(Album album) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	
	public void deleteAlbum(Album album) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SQLException;
	
	public void updateAlbum(Album album) throws SQLException;
	
	public void deleteAlbumById(int id) throws SQLException;
}