package com.tao.player.util;

import java.io.InputStream;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {
	private static Map<String, String> propertiesMap = new Hashtable<String, String>();

	public static String getProperty(String propertyName) {
		try {
			if ((propertiesMap.size() == 0) || (propertiesMap.get(propertyName) == null)) {
				propertiesMap.clear();
				InputStream is = PropertiesUtil.class.getResourceAsStream("/db.properties");
				Properties p = new Properties();
				p.load(is);
				Iterator<Object> it = p.keySet().iterator();
				while (it.hasNext()) {
					String key = (String) it.next();
					propertiesMap.put(key, p.getProperty(key));
				}
				System.out.println("Properties: " + propertiesMap);
				p.clear();
				is.close();
			}
			return (String) propertiesMap.get(propertyName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static void main(String[] args) {
		System.out.println(getProperty("db.username"));
	}
}
