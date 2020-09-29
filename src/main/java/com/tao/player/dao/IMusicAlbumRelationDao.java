package com.tao.player.dao;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import com.tao.player.pojo.MusicAlbumRelation;

public interface IMusicAlbumRelationDao {

	public List<MusicAlbumRelation> findAllmusicAlbumRelations() throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException;
	
	public MusicAlbumRelation findMusicAlbumRelationById(int id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException;
	
	public void addMusicAlbumRelation(MusicAlbumRelation musicAlbumRelation) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	
	public void deleteMusicAlbumRelation(MusicAlbumRelation musicAlbumRelation) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SQLException;
	
	public void updateMusicAlbumRelation(MusicAlbumRelation musicAlbumRelation) throws SQLException;
	
	public void deleteMusicAlbumRelationById(int id) throws SQLException;
}