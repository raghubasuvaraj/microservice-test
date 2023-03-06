package com.sta.dc.parent.utils;

/**
 * Redis key design
 * 
 * @author r@ghu
 *
 */
public class RedisKeyUtils {

	/**
	 * The key form of redis is: table name: primary key name: primary key value:
	 * column name
	 *
	 * @param tableName     table name
	 * @param majorKey      primary key name
	 * @param majorKeyValue primary key value
	 * @param column        column name
	 * @return
	 */
	public static String getKeyWithColumn(String tableName, String majorKey, String majorKeyValue, String column) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(tableName).append(":");
		buffer.append(majorKey).append(":");
		buffer.append(majorKeyValue).append(":");
		buffer.append(column);
		return buffer.toString();
	}

	/**
	 * redis key in the form: table name: primary key name: primary key value
	 *
	 * @param tableName     table name
	 * @param majorKey      primary key name
	 * @param majorKeyValue primary key value
	 * @return
	 */
	public static String getKey(String tableName, String majorKey, String majorKeyValue) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(tableName).append(":");
		buffer.append(majorKey).append(":");
		buffer.append(majorKeyValue).append(":");
		return buffer.toString();
	}

}
