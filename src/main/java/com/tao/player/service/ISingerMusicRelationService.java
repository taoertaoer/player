package com.tao.player.service;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import com.tao.player.pojo.SingerMusicRelation;

public interface ISingerMusicRelationService {

	public SingerMusicRelation findSingerMusicRelationById(int id) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException;
	
	public void addSingerMusicRelation(SingerMusicRelation singerMusicRelation) throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException;
	
	public void updateSingerMusicRelation(SingerMusicRelation singerMusicRelation) throws SQLException;
	
	public void deleteSingerMusicRelationById(int id) throws SQLException;
}