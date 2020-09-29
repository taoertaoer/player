package com.tao.player.dao;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import com.tao.player.pojo.Singer;

public interface ISingerDao {

	public List<Singer> findAllsingers() throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException;
	
	public Singer findSingerById(int id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException;
	
	public void addSinger(Singer singer) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	
	public void deleteSinger(Singer singer) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SQLException;
	
	public void updateSinger(Singer singer) throws SQLException;
	
	public void deleteSingerById(int id) throws SQLException;
}