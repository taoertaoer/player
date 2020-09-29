package com.tao.player.dao;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import com.tao.player.pojo.SingerAlbumRelation;

public interface ISingerAlbumRelationDao {

	public List<SingerAlbumRelation> findAllsingerAlbumRelations() throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException;
	
	public SingerAlbumRelation findSingerAlbumRelationById(int id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException;
	
	public void addSingerAlbumRelation(SingerAlbumRelation singerAlbumRelation) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	
	public void deleteSingerAlbumRelation(SingerAlbumRelation singerAlbumRelation) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SQLException;
	
	public void updateSingerAlbumRelation(SingerAlbumRelation singerAlbumRelation) throws SQLException;
	
	public void deleteSingerAlbumRelationById(int id) throws SQLException;
}