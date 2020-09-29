package com.tao.player.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.tao.player.dao.IAlbumDao;
import com.tao.player.pojo.Album;
import com.tao.player.util.BeanUtil;
import com.tao.player.util.jdbc.JdbcUtil;

public class AlbumDao implements IAlbumDao {

	@Override
	public List<Album> findAllalbums()
			throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException,
			SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		List<Album> list = JdbcUtil.findAll(Album.class);
		return list;
	}

	@Override
	public Album findAlbumById(int id)
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SQLException {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("id", id);
		List<Album> list = JdbcUtil.findByMap(Album.class, parameters, null);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void addAlbum(Album album) throws SQLException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		if (album.getIsValid() == null) {
			album.setIsValid("Y");
		}
		JdbcUtil.add(album);
	}

	@Override
	public void deleteAlbum(Album album) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, SQLException {
		JdbcUtil.delete(album);
	}

	@Override
	public void updateAlbum(Album album) throws SQLException {
		if (album.getIsValid() == null) {
			album.setIsValid("Y");
		}
		Map<String, Object> values = new HashMap<String, Object>();
		BeanUtil.obj2Map(values, album);
		values.remove("id");
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("id", album.getId());
		JdbcUtil.updateByMap(album.getClass(), values, conditions);
	}

	@Override
	public void deleteAlbumById(int id) throws SQLException {
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("id", id);
		JdbcUtil.deleteByMap(Album.class, conditions);
	}
}