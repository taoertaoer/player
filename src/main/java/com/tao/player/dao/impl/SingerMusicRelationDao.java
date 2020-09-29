package com.tao.player.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tao.player.dao.ISingerMusicRelationDao;
import com.tao.player.pojo.SingerMusicRelation;
import com.tao.player.util.BeanUtil;
import com.tao.player.util.jdbc.JdbcUtil;

public class SingerMusicRelationDao implements ISingerMusicRelationDao {

	@Override
	public List<SingerMusicRelation> findAllsingerMusicRelations()
			throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException,
			SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		List<SingerMusicRelation> list = JdbcUtil.findAll(SingerMusicRelation.class);
		return list;
	}

	@Override
	public SingerMusicRelation findSingerMusicRelationById(int id)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("id", id);
		List<SingerMusicRelation> list = JdbcUtil.findByMap(SingerMusicRelation.class, parameters, null);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void addSingerMusicRelation(SingerMusicRelation singerMusicRelation)
			throws SQLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		JdbcUtil.add(singerMusicRelation);
	}

	@Override
	public void deleteSingerMusicRelation(SingerMusicRelation singerMusicRelation)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException, SQLException {
		JdbcUtil.delete(singerMusicRelation);
	}

	@Override
	public void updateSingerMusicRelation(SingerMusicRelation singerMusicRelation) throws SQLException {
		Map<String, Object> values = new HashMap<String, Object>();
		BeanUtil.obj2Map(values, singerMusicRelation);
		values.remove("id");
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("id", singerMusicRelation.getId());
		JdbcUtil.updateByMap(singerMusicRelation.getClass(), values, conditions);
	}

	@Override
	public void deleteSingerMusicRelationById(int id) throws SQLException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("id", id);
		JdbcUtil.deleteByMap(SingerMusicRelation.class, conditions);
	}
}