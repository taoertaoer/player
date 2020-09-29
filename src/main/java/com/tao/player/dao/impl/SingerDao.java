package com.tao.player.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tao.player.dao.ISingerDao;
import com.tao.player.pojo.Singer;
import com.tao.player.util.BeanUtil;
import com.tao.player.util.jdbc.JdbcUtil;

public class SingerDao implements ISingerDao {

	@Override
	public List<Singer> findAllsingers()
			throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException,
			SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		List<Singer> list = JdbcUtil.findAll(Singer.class);
		return list;
	}

	@Override
	public Singer findSingerById(int id)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("id", id);
		List<Singer> list = JdbcUtil.findByMap(Singer.class, parameters, null);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void addSinger(Singer singer) throws SQLException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		if (singer.getIsValid() == null) {
			singer.setIsValid("Y");
		}
		JdbcUtil.add(singer);
	}

	@Override
	public void deleteSinger(Singer singer) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, SQLException {
		JdbcUtil.delete(singer);
	}

	@Override
	public void updateSinger(Singer singer) throws SQLException {
		if (singer.getIsValid() == null) {
			singer.setIsValid("Y");
		}
		Map<String, Object> values = new HashMap<String, Object>();
		BeanUtil.obj2Map(values, singer);
		values.remove("id");
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("id", singer.getId());
		JdbcUtil.updateByMap(singer.getClass(), values, conditions);
	}

	@Override
	public void deleteSingerById(int id) throws SQLException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("id", id);
		JdbcUtil.deleteByMap(Singer.class, conditions);
	}
}