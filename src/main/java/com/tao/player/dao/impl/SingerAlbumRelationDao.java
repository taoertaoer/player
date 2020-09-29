package com.tao.player.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tao.player.dao.ISingerAlbumRelationDao;
import com.tao.player.pojo.SingerAlbumRelation;
import com.tao.player.util.BeanUtil;
import com.tao.player.util.jdbc.JdbcUtil;

public class SingerAlbumRelationDao implements ISingerAlbumRelationDao {

	@Override
	public List<SingerAlbumRelation> findAllsingerAlbumRelations()
			throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException,
			SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		List<SingerAlbumRelation> list = JdbcUtil.findAll(SingerAlbumRelation.class);
		return list;
	}

	@Override
	public SingerAlbumRelation findSingerAlbumRelationById(int id)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("id", id);
		List<SingerAlbumRelation> list = JdbcUtil.findByMap(SingerAlbumRelation.class, parameters, null);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void addSingerAlbumRelation(SingerAlbumRelation singerAlbumRelation)
			throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		JdbcUtil.add(singerAlbumRelation);
	}

	@Override
	public void deleteSingerAlbumRelation(SingerAlbumRelation singerAlbumRelation)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException, SQLException {
		JdbcUtil.delete(singerAlbumRelation);
	}

	@Override
	public void updateSingerAlbumRelation(SingerAlbumRelation singerAlbumRelation) throws SQLException {
		Map<String, Object> values = new HashMap<String, Object>();
		BeanUtil.obj2Map(values, singerAlbumRelation);
		values.remove("id");
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("id", singerAlbumRelation.getId());
		JdbcUtil.updateByMap(singerAlbumRelation.getClass(), values, conditions);
	}

	@Override
	public void deleteSingerAlbumRelationById(int id) throws SQLException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("id", id);
		JdbcUtil.deleteByMap(SingerAlbumRelation.class, conditions);
	}
}