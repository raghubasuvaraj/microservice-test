//package com.sta.dc.parent.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.GenericToStringSerializer;
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.*;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
///**
// * With the help of RedisStandaloneConfiguration will make a login connection to
// * the Redis-client, redis.properties file contains all the credentials.
// *
// * @author r@ghu
// *
// * @see org.springframework.boot.autoconfigure.condition for @ConditionalOnProperty
// *
// */
//@Configuration
//@EnableCaching
//@PropertySource("classpath:redis.properties")
//@ConditionalOnProperty(prefix = "spring.redis", name = "host-port-password-database-pool.max-idle")
//public class RedisConfig {
//
//	@Value("${spring.redis.host}")
//	private String redisHost;
//
//	@Value("${spring.redis.port}")
//	private Integer redisPort;
//
//	@Value("spring.redis.password")
//	private String redisPassword;
//
//	@Value("${spring.redis.database}")
//	private Integer redisDatabase;
//
//	@Value("${spring.redis.pool.max-idle}")
//	private Integer redisMaxIdle;
//
//	/**
//	 * Establishing a connection with redis
//	 *
//	 * @return
//	 */
//	@Bean
//	public JedisConnectionFactory jedisConnectionFactory() {
//		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//		redisStandaloneConfiguration.setHostName(redisHost);
//		redisStandaloneConfiguration.setPort(redisPort);
//		redisStandaloneConfiguration.setPassword(redisPassword);
//		redisStandaloneConfiguration.setDatabase(redisDatabase);
//		return new JedisConnectionFactory(redisStandaloneConfiguration);
//	}
//
//	@Bean
//	public RedisTemplate<String, Object> redisTemplate() {
//		final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
//		template.setConnectionFactory(jedisConnectionFactory());
//		template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
//		return template;
//	}
//
//	/**
//	 * Choose redis as the default caching tool
//	 *
//	 * @param redisTemplate
//	 * @return
//	 */
////    @Bean
////    public CacheManager cacheManager(RedisTemplate redisTemplate) {
////        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
////        return rcm;
////    }
//
//	/**
//	 * retemplate related configuration
//	 *
//	 * @param factory
//	 * @return
//	 */
//	@SuppressWarnings({ "unchecked", "deprecation" })
//	@Bean
//	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
//
//		RedisTemplate<String, Object> template = new RedisTemplate<>();
//		// Configure the connection factory
//		template.setConnectionFactory(factory);
//
//		// Use Jackson2JsonRedisSerializer to serialize and deserialize the value of
//		// redis (the default serialization method of JDK)
//		@SuppressWarnings("rawtypes")
//		Jackson2JsonRedisSerializer jacksonSeial = new Jackson2JsonRedisSerializer(Object.class);
//
//		ObjectMapper om = new ObjectMapper();
//
//		// Specify the field to be serialized, field, get and set, as well as the
//		// modifier range, ANY includes private and public
//		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//
//		// Specify the type of serialized input. The class must be non-final modified.
//		// Final modified classes, such as String, Integer, etc., will run out of
//		// exceptions
//		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//
//		jacksonSeial.setObjectMapper(om);
//
//		// The value is serialized in json
//		template.setValueSerializer(jacksonSeial);
//
//		// Use StringRedisSerializer to serialize and deserialize redis key values
//		template.setKeySerializer(new StringRedisSerializer());
//
//		// Set hash key and value serialization mode
//		template.setHashKeySerializer(new StringRedisSerializer());
//		template.setHashValueSerializer(jacksonSeial);
//		template.afterPropertiesSet();
//
//		return template;
//	}
//
//	/**
//	 * Data operations on hash types
//	 *
//	 * @param redisTemplate
//	 * @return
//	 */
//	@Bean
//	public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
//		return redisTemplate.opsForHash();
//	}
//
//	/**
//	 * Operations on redis string type data
//	 *
//	 * @param redisTemplate
//	 * @return
//	 */
//	@Bean
//	public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
//		return redisTemplate.opsForValue();
//	}
//
//	/**
//	 * Data operations on linked list types
//	 *
//	 * @param redisTemplate
//	 * @return
//	 */
//	@Bean
//	public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
//		return redisTemplate.opsForList();
//	}
//
//	/**
//	 * Data operations on unordered collection types
//	 *
//	 * @param redisTemplate
//	 * @return
//	 */
//	@Bean
//	public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
//		return redisTemplate.opsForSet();
//	}
//
//	/**
//	 * Data Operations on Sorted Collection Types
//	 *
//	 * @param redisTemplate
//	 * @return
//	 */
//	@Bean
//	public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
//		return redisTemplate.opsForZSet();
//	}
//}
