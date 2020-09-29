package com.tao.player.service;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import com.tao.player.pojo.MusicAlbumRelation;

public interface IMusicAlbumRelationService {

	public MusicAlbumRelation findMusicAlbumRelationById(int id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException;
	
	public void addMusicAlbumRelation(MusicAlbumRelation musicAlbumRelation) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	
	public void updateMusicAlbumRelation(MusicAlbumRelation musicAlbumRelation) throws SQLException;
	
	public void deleteMusicAlbumRelationById(int id) throws SQLException;
}