package com.tao.player.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tao.player.dao.IMusicDao;
import com.tao.player.pojo.Music;
import com.tao.player.util.BeanUtil;
import com.tao.player.util.jdbc.JdbcUtil;

public class MusicDao implements IMusicDao {

	@Override
	public List<Music> findAllmusics()
			throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException,
			SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		List<Music> list = JdbcUtil.findAll(Music.class);
		return list;
	}

	@Override
	public Music findMusicById(int id)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("id", id);
		List<Music> list = JdbcUtil.findByMap(Music.class, parameters, null);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void addMusic(Music music) throws SQLException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		if (music.getIsValid() == null) {
			music.setIsValid("Y");
		}
		JdbcUtil.add(music);
	}

	@Override
	public void deleteMusic(Music music) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, SQLException {
		JdbcUtil.delete(music);
	}

	@Override
	public void updateMusic(Music music) throws SQLException {
		if (music.getIsValid() == null) {
			music.setIsValid("Y");
		}
		Map<String, Object> values = new HashMap<String, Object>();
		BeanUtil.obj2Map(values, music);
		values.remove("id");
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("id", music.getId());
		JdbcUtil.updateByMap(music.getClass(), values, conditions);
	}

	@Override
	public void deleteMusicById(int id) throws SQLException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("id", id);
		JdbcUtil.deleteByMap(Music.class, conditions);
	}
}