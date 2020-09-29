package com.tao.player.service;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import com.tao.player.pojo.Singer;

public interface ISingerService {

	public Singer findSingerById(int id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException;
	
	public void addSinger(Singer singer) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	
	public void updateSinger(Singer singer) throws SQLException;
	
	public void deleteSingerById(int id) throws SQLException;
}