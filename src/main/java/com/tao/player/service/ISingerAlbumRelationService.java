package com.tao.player.service;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import com.tao.player.pojo.SingerAlbumRelation;

public interface ISingerAlbumRelationService {

	public SingerAlbumRelation findSingerAlbumRelationById(int id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException;
	
	public void addSingerAlbumRelation(SingerAlbumRelation singerAlbumRelation) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	
	public void updateSingerAlbumRelation(SingerAlbumRelation singerAlbumRelation) throws SQLException;
	
	public void deleteSingerAlbumRelationById(int id) throws SQLException;
}