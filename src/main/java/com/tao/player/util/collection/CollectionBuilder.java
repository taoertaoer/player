package com.tao.player.util.collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

public class CollectionBuilder {

	public static <K, V> MapBuilder<K, V> builder(Map<K, V> map) {
		return new MapBuilder<K, V>(map);
	}
	
	public static <V> JSONObjectBuilder<V> builder(JSONObject json) {
		return new JSONObjectBuilder<V>(json);
	}
	
	public static <V> ListBuilder<V> builder(List<V> map) {
		return new ListBuilder<V>(map);
	}
	
	public static <V> SetBuilder<V> builder(Set<V> map) {
		return new SetBuilder<V>(map);
	}

	public static class MapBuilder<K, V> {
		private Map<K, V> map;
		private boolean underConstruction;

		private MapBuilder(Map<K, V> map) {
			this.map = map;
			this.underConstruction = true;
		}

		public MapBuilder<K, V> put(K key, V value) {
			if (!this.underConstruction) {
				throw new IllegalStateException("Underlying map has already been built");
			}
			this.map.put(key, value);
			return this;
		}

		public Map<K, V> build() {
			if (!this.underConstruction) {
				throw new IllegalStateException("Underlying map has already been built");
			}
			this.underConstruction = false;
			return this.map;
		}
	}
	
	public static class JSONObjectBuilder<V> {
		private JSONObject json;
		private boolean underConstruction;

		private JSONObjectBuilder(JSONObject json) {
			this.json = json;
			this.underConstruction = true;
		}

		public JSONObjectBuilder<V> put(String key, V value) {
			if (!this.underConstruction) {
				throw new IllegalStateException("Underlying map has already been built");
			}
			this.json.put(key, value);
			return this;
		}

		public JSONObject build() {
			if (!this.underConstruction) {
				throw new IllegalStateException("Underlying map has already been built");
			}
			this.underConstruction = false;
			return this.json;
		}
	}
	
	public static class ListBuilder<V> {
		private List<V> list;
		private boolean underConstruction;

		private ListBuilder(List<V> list) {
			this.list = list;
			this.underConstruction = true;
		}

		public ListBuilder<V> add(V value) {
			if (!this.underConstruction) {
				throw new IllegalStateException("Underlying map has already been built");
			}
			this.list.add(value);
			return this;
		}

		public List<V> build() {
			if (!this.underConstruction) {
				throw new IllegalStateException("Underlying map has already been built");
			}
			this.underConstruction = false;
			return this.list;
		}
	}
	
	public static class SetBuilder<V> {
		private Set<V> set;
		private boolean underConstruction;

		private SetBuilder(Set<V> set) {
			this.set = set;
			this.underConstruction = true;
		}

		public SetBuilder<V> put(V value) {
			if (!this.underConstruction) {
				throw new IllegalStateException("Underlying map has already been built");
			}
			this.set.add(value);
			return this;
		}

		public Set<V> build() {
			if (!this.underConstruction) {
				throw new IllegalStateException("Underlying map has already been built");
			}
			this.underConstruction = false;
			return this.set;
		}
	}

	public static void main(String[] args) {
		System.out.println(
				CollectionBuilder.builder(new HashMap<String, String>()).put("1", "ddd").put("2", "fff").build());
	}
}
