package com.sta.dc.parent.component;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author r@ghu
 *
 */
@Component
@PropertySource("classpath:redis.properties")
@ConditionalOnProperty(prefix = "spring.redis", name = "timeout-pool.max-wait")
public class RedisComponent {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	/**
	 * Default expiration time, unit: seconds
	 */
	@Value("${spring.redis.timeout}")
	public long timeout;

	/**
	 * Do not set expiration time
	 */
	@Value("${spring.redis.pool.max-wait}")
	public long maxWait;

	public boolean existsKey(String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * Rename the key, if newKey already exists, the original value of newKey will
	 * be overwritten.
	 * 
	 * @param oldKey
	 * @param newKey
	 */
	public void renameKey(String oldKey, String newKey) {
		redisTemplate.rename(oldKey, newKey);
	}

	/**
	 * Rename only when newKey does not exist
	 * 
	 * @param oldKey
	 * @param newKey
	 * @return true if the modification is successful
	 */
	public boolean renameKeyNotExist(String oldKey, String newKey) {
		return redisTemplate.renameIfAbsent(oldKey, newKey);
	}

	/**
	 * delete key
	 * 
	 * @param key
	 */
	public void deleteKey(String key) {
		redisTemplate.delete(key);
	}

	/**
	 * Delete multiple keys
	 * 
	 * @param keys
	 */
	public void deleteKey(String... keys) {
		Set<String> kSet = Stream.of(keys).map(k -> k).collect(Collectors.toSet());
		redisTemplate.delete(kSet);
	}

	/**
	 * DElete key collections
	 * 
	 * @param keys
	 */
	public void deleteKey(Collection<String> keys) {
		Set<String> kSet = keys.stream().map(k -> k).collect(Collectors.toSet());
		redisTemplate.delete(kSet);
	}

	/**
	 * Set the life cycle of the key
	 *
	 * @param key
	 * @param time
	 * @param timeUnit
	 */
	public void expireKey(String key, long time, TimeUnit timeUnit) {
		redisTemplate.expire(key, time, timeUnit);
	}

	/**
	 * The specified key expires on the specified date
	 *
	 * @param key
	 * @param date
	 */
	public void expireKeyAt(String key, Date date) {
		redisTemplate.expireAt(key, date);
	}

	/**
	 * Query the life cycle of the key
	 *
	 * @param key
	 * @param timeUnit
	 * @return
	 */
	public long getKeyExpire(String key, TimeUnit timeUnit) {
		return redisTemplate.getExpire(key, timeUnit);
	}

	/**
	 * Set the key to be permanently valid
	 *
	 * @param key
	 */
	public void persistKey(String key) {
		redisTemplate.persist(key);
	}

}
