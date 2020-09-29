package com.tao.player.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import com.tao.player.dao.ISingerAlbumRelationDao;
import com.tao.player.dao.impl.SingerAlbumRelationDao;
import com.tao.player.pojo.SingerAlbumRelation;
import com.tao.player.service.ISingerAlbumRelationService;

public class SingerAlbumRelationService implements ISingerAlbumRelationService {

	private ISingerAlbumRelationDao singerAlbumRelationDao = new SingerAlbumRelationDao();

	@Override
	public SingerAlbumRelation findSingerAlbumRelationById(int id)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException {
		return singerAlbumRelationDao.findSingerAlbumRelationById(id);
	}

	@Override
	public void addSingerAlbumRelation(SingerAlbumRelation singerAlbumRelation)
			throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		singerAlbumRelationDao.addSingerAlbumRelation(singerAlbumRelation);
	}

	@Override
	public void updateSingerAlbumRelation(SingerAlbumRelation singerAlbumRelation) throws SQLException {
		singerAlbumRelationDao.updateSingerAlbumRelation(singerAlbumRelation);
	}

	@Override
	public void deleteSingerAlbumRelationById(int id) throws SQLException {
		singerAlbumRelationDao.deleteSingerAlbumRelationById(id);
	}
}