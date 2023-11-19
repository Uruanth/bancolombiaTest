package co.com.onneq.redis.template;

import co.com.onneq.redis.template.user.UserRedis;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration
public class RedisConfig {

    @Bean
    public ReactiveRedisTemplate<String, UserRedis>  reactiveRedisTemplate(ReactiveRedisConnectionFactory factory){
        return new ReactiveRedisTemplate<String, UserRedis>(
                factory,
                RedisSerializationContext.fromSerializer(new Jackson2JsonRedisSerializer(UserRedis.class))
        );
    }

    @Bean
    public ReactiveHashOperations<String, Integer, UserRedis> reactiveHashOperations(
            ReactiveRedisTemplate<String, UserRedis> reactiveRedisTemplate) {
        return reactiveRedisTemplate.opsForHash();
    }
}

