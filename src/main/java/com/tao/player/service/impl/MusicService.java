package com.tao.player.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import com.tao.player.dao.IMusicDao;
import com.tao.player.dao.impl.MusicDao;
import com.tao.player.pojo.Music;
import com.tao.player.service.IMusicService;

public class MusicService implements IMusicService {

	private IMusicDao musicDao = new MusicDao();

	@Override
	public Music findMusicById(int id)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException {
		return musicDao.findMusicById(id);
	}

	@Override
	public void addMusic(Music music) throws SQLException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		musicDao.addMusic(music);
	}

	@Override
	public void updateMusic(Music music) throws SQLException {
		musicDao.updateMusic(music);
	}

	@Override
	public void deleteMusicById(int id) throws SQLException {
		musicDao.deleteMusicById(id);
	}
}