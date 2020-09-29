package com.tao.player.service;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import com.tao.player.pojo.Music;

public interface IMusicService {

	public Music findMusicById(int id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException;
	
	public void addMusic(Music music) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	
	public void updateMusic(Music music) throws SQLException;
	
	public void deleteMusicById(int id) throws SQLException;
}