package com.tao.player.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import com.tao.player.dao.ISingerDao;
import com.tao.player.dao.impl.SingerDao;
import com.tao.player.pojo.Singer;
import com.tao.player.service.ISingerService;

public class SingerService implements ISingerService {

	private ISingerDao singerDao = new SingerDao();

	@Override
	public Singer findSingerById(int id)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException {
		return singerDao.findSingerById(id);
	}

	@Override
	public void addSinger(Singer singer) throws SQLException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		singerDao.addSinger(singer);
	}

	@Override
	public void updateSinger(Singer singer) throws SQLException {
		singerDao.updateSinger(singer);
	}

	@Override
	public void deleteSingerById(int id) throws SQLException {
		singerDao.deleteSingerById(id);
	}
}