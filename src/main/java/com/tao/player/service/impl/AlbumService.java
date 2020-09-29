package com.tao.player.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import com.tao.player.dao.IAlbumDao;
import com.tao.player.dao.impl.AlbumDao;
import com.tao.player.pojo.Album;
import com.tao.player.service.IAlbumService;

public class AlbumService implements IAlbumService {

	private IAlbumDao albumDao = new AlbumDao();

	@Override
	public Album findAlbumById(int id)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException {
		return albumDao.findAlbumById(id);
	}

	@Override
	public void addAlbum(Album album) throws SQLException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		albumDao.addAlbum(album);
	}

	@Override
	public void updateAlbum(Album album) throws SQLException {
		albumDao.updateAlbum(album);
	}

	@Override
	public void deleteAlbumById(int id) throws SQLException {
		albumDao.deleteAlbumById(id);
	}
}