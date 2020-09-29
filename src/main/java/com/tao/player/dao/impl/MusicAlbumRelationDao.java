package com.tao.player.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tao.player.dao.IMusicAlbumRelationDao;
import com.tao.player.pojo.MusicAlbumRelation;
import com.tao.player.util.BeanUtil;
import com.tao.player.util.jdbc.JdbcUtil;

public class MusicAlbumRelationDao implements IMusicAlbumRelationDao {

	@Override
	public List<MusicAlbumRelation> findAllmusicAlbumRelations()
			throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException,
			SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		List<MusicAlbumRelation> list = JdbcUtil.findAll(MusicAlbumRelation.class);
		return list;
	}

	@Override
	public MusicAlbumRelation findMusicAlbumRelationById(int id)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("id", id);
		List<MusicAlbumRelation> list = JdbcUtil.findByMap(MusicAlbumRelation.class, parameters, null);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void addMusicAlbumRelation(MusicAlbumRelation musicAlbumRelation)
			throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		JdbcUtil.add(musicAlbumRelation);
	}

	@Override
	public void deleteMusicAlbumRelation(MusicAlbumRelation musicAlbumRelation)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException, SQLException {
		JdbcUtil.delete(musicAlbumRelation);
	}

	@Override
	public void updateMusicAlbumRelation(MusicAlbumRelation musicAlbumRelation) throws SQLException {
		Map<String, Object> values = new HashMap<String, Object>();
		BeanUtil.obj2Map(values, musicAlbumRelation);
		values.remove("id");
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("id", musicAlbumRelation.getId());
		JdbcUtil.updateByMap(musicAlbumRelation.getClass(), values, conditions);
	}

	@Override
	public void deleteMusicAlbumRelationById(int id) throws SQLException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("id", id);
		JdbcUtil.deleteByMap(MusicAlbumRelation.class, conditions);
	}
}