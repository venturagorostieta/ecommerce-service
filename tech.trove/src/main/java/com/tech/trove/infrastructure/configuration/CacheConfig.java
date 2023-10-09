package com.tech.trove.infrastructure.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.support.NullValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.io.IOException;
import java.time.Duration;

/**
 * The type Cache config.
 */
@Configuration
public class CacheConfig extends CachingConfigurerSupport {

    /**
     * Cache manager redis cache manager.
     *
     * @param redisConnectionFactory the redis connection factory
     * @return the redis cache manager
     */
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig(Thread.currentThread().getContextClassLoader())
                .entryTtl(Duration.ofMinutes(10));

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(cacheConfiguration)
                .build();
    }

    /**
     * Jackson serializer for redis cache redis serialization context . serialization pair.
     *
     * @return the redis serialization context . serialization pair
     */
    @Bean
    public static RedisSerializationContext.SerializationPair<Object> jacksonSerializerForRedisCache() {
        final ObjectMapper mapper = new ObjectMapper()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .enable(MapperFeature.DEFAULT_VIEW_INCLUSION)
                .setSerializationInclusion(JsonInclude.Include.NON_ABSENT)
                .enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY)
                .registerModules(
                        NullValueSerializer.asModule(),
                        new JavaTimeModule(),
                        new Jdk8Module());
        final GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer(mapper);
        return RedisSerializationContext.SerializationPair.fromSerializer(serializer);
    }

    /**
     * Spring's Cacheable/CacheInterceptor does not provide type information to the
     * RedisSerializationContext, and for that reason jackson must bookkeep typing information at all times.
     * While `enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY)` does help
     * to keep a meta-data property `@class` , it is not enough to deserialize null values.
     */
    public static class NullValueSerializer extends StdSerializer<NullValue> {

        /**
         * As module simple module.
         *
         * @return the simple module
         */
        public static SimpleModule asModule() {
            return new SimpleModule().addSerializer(new NullValueSerializer());
        }

        /**
         * Instantiates a new Null value serializer.
         */
        public NullValueSerializer() {
            super(NullValue.class);
        }
        @Override
        public void serialize(NullValue value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeStartObject();
            jgen.writeStringField("@class", NullValue.class.getName());
            jgen.writeEndObject();
        }
    }

}
