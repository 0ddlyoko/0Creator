package me.oddlyoko.zeroCreator;

import java.util.HashMap;
import java.util.Map;

public class Parameters { // TODO WHY DID I CREATE THIS CLASS ???? REMOVE IT ?
	private Map<String, Object> maps = new HashMap<>();

	public void set(String key, Object value) {
		maps.put(key, value);
	}

	public Object get(String key) {
		return maps.get(key);
	}

	public boolean getBoolean(String key) {
		Object value = get(key);
		return value == null ? false : (boolean) value;
	}

	public int getInteger(String key) {
		Object value = get(key);
		return value == null ? 0 : (int) value;
	}

	public float getFloat(String key) {
		Object value = get(key);
		return value == null ? 0 : (float) value;
	}

	public double getDouble(String key) {
		Object value = get(key);
		return value == null ? 0 : (double) value;
	}

	public char getChar(String key) {
		Object value = get(key);
		return value == null ? 0 : (char) value;
	}

	public String getString(String key) {
		Object value = get(key);
		return value == null ? "" : (String) value;
	}
}
