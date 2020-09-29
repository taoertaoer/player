package com.tao.player.dao;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import com.tao.player.pojo.SingerMusicRelation;

public interface ISingerMusicRelationDao {

	public List<SingerMusicRelation> findAllsingerMusicRelations() throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException;
	
	public SingerMusicRelation findSingerMusicRelationById(int id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException;
	
	public void addSingerMusicRelation(SingerMusicRelation singerMusicRelation) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	
	public void deleteSingerMusicRelation(SingerMusicRelation singerMusicRelation) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SQLException;
	
	public void updateSingerMusicRelation(SingerMusicRelation singerMusicRelation) throws SQLException;
	
	public void deleteSingerMusicRelationById(int id) throws SQLException;
}