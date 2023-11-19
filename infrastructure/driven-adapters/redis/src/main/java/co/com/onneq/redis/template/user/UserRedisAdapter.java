package co.com.onneq.redis.template.user;

import co.com.onneq.model.user.User;
import co.com.onneq.model.user.gateways.UserCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserRedisAdapter implements UserCacheRepository {

    private final ReactiveRedisOperations<String, UserRedis> reactiveRedisOperations;


    @Override
    public Flux<User> getAll() {
        return reactiveRedisOperations.opsForList().range("users", 0, -1)
                .map(userRedis -> User.builder()
                        .id(userRedis.getUserId())
                        .lastName(userRedis.getLastName())
                        .name(userRedis.getName())
                        .build());
    }

    @Override
    public Mono<Long> save(User user){
        var uRedis = UserRedis.builder()
                .userId(user.getId())
                .lastName(user.getLastName())
                .name(user.getName())
                .build();
        return this.reactiveRedisOperations.opsForList().rightPush("users", uRedis);
    }

    @Override
    public Mono<Boolean> deleteAll() {
        return reactiveRedisOperations.opsForList().delete("users");
    }
}
