package com.tao.player.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import com.tao.player.dao.ISingerMusicRelationDao;
import com.tao.player.dao.impl.SingerMusicRelationDao;
import com.tao.player.pojo.SingerMusicRelation;
import com.tao.player.service.ISingerMusicRelationService;

public class SingerMusicRelationService implements ISingerMusicRelationService {

	private ISingerMusicRelationDao singerMusicRelationDao = new SingerMusicRelationDao();

	@Override
	public SingerMusicRelation findSingerMusicRelationById(int id)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException {
		return singerMusicRelationDao.findSingerMusicRelationById(id);
	}

	@Override
	public void addSingerMusicRelation(SingerMusicRelation singerMusicRelation)
			throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		singerMusicRelationDao.addSingerMusicRelation(singerMusicRelation);
	}

	@Override
	public void updateSingerMusicRelation(SingerMusicRelation singerMusicRelation) throws SQLException {
		singerMusicRelationDao.updateSingerMusicRelation(singerMusicRelation);
	}

	@Override
	public void deleteSingerMusicRelationById(int id) throws SQLException {
		singerMusicRelationDao.deleteSingerMusicRelationById(id);
	}
}