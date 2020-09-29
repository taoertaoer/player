package com.tao.player.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import com.tao.player.dao.IMusicAlbumRelationDao;
import com.tao.player.dao.impl.MusicAlbumRelationDao;
import com.tao.player.pojo.MusicAlbumRelation;
import com.tao.player.service.IMusicAlbumRelationService;

public class MusicAlbumRelationService implements IMusicAlbumRelationService {

	private IMusicAlbumRelationDao musicAlbumRelationDao = new MusicAlbumRelationDao();

	@Override
	public MusicAlbumRelation findMusicAlbumRelationById(int id)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException {
		return musicAlbumRelationDao.findMusicAlbumRelationById(id);
	}

	@Override
	public void addMusicAlbumRelation(MusicAlbumRelation musicAlbumRelation)
			throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		musicAlbumRelationDao.addMusicAlbumRelation(musicAlbumRelation);
	}

	@Override
	public void updateMusicAlbumRelation(MusicAlbumRelation musicAlbumRelation) throws SQLException {
		musicAlbumRelationDao.updateMusicAlbumRelation(musicAlbumRelation);
	}

	@Override
	public void deleteMusicAlbumRelationById(int id) throws SQLException {
		musicAlbumRelationDao.deleteMusicAlbumRelationById(id);
	}
}