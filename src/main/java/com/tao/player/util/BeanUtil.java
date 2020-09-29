package com.tao.player.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanUtil {

	public static <T> T map2Obj(T obj, Map<String, Object> map) {

		Field[] fields = obj.getClass().getDeclaredFields();
		try {
			Class<? extends Object> cla = obj.getClass();
			for (int i = 0; i < fields.length; i++) {
				String varName = fields[i].getName();
				if (map.containsKey(varName)) {
					Method method = cla.getDeclaredMethod(
							"set" + varName.substring(0, 1).toUpperCase() + varName.substring(1), fields[i].getType());
					method.invoke(obj, map.get(varName));
				}
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return obj;
	}

	public static Map<String, Object> obj2Map(Map<String, Object> map, Object obj) {

		if(obj == null) {
			return map;
		}
		Field[] fields = obj.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			String varName = fields[i].getName();

			try {
				boolean accessFlag = fields[i].isAccessible();
				fields[i].setAccessible(true);
				Object o = fields[i].get(obj);
				
				if(!"serialVersionUID".equals(varName))map.put(varName, o == null ? null : o);
				
				fields[i].setAccessible(accessFlag);
			} catch (IllegalArgumentException ex) {
				ex.printStackTrace();
			} catch (IllegalAccessException ex) {
				ex.printStackTrace();
			}
		}
		return map;
	}

	public static Map<String, Object> objs2Map(Map<String, Object> result, Object obj, Object... objs) {
		result = obj2Map(result, obj);
		if (objs.length > 0) {
			for (int i = 0; i < objs.length; i++) {
				Map<String, Object> m = new HashMap<String, Object>();
				m = obj2Map(m, objs[i]);
				for (String str : m.keySet()) {
					String k = str;
					if (result.containsKey(str)) {
						String c_name = objs[i].getClass().getName();
						k = c_name.substring(c_name.lastIndexOf(".") + 1) + "_" + str;
					}
					result.put(k, m.get(str));
				}
			}
		}
		return result;
	}
}
