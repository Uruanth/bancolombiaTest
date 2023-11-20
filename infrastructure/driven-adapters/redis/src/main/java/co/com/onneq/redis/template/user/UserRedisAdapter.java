package co.com.onneq.redis.template.user;

import co.com.onneq.model.user.User;
import co.com.onneq.model.user.gateways.UserCacheRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log
@Service
@RequiredArgsConstructor
public class UserRedisAdapter implements UserCacheRepository {

    private final ReactiveHashOperations<String, Integer, UserRedis> reactiveRedisOperations;


    @Override
    public Flux<User> getAll() {

        return reactiveRedisOperations.values("users")
                .map(userRedis -> User.builder()
                        .id(userRedis.getUserId())
                        .lastName(userRedis.getLastName())
                        .name(userRedis.getName())
                        .build());
    }

    @Override
    public Mono<User> getById(Integer id) {
        return reactiveRedisOperations
                .get("users", id)
                .map(userRedis -> User.builder()
                        .id(userRedis.getUserId())
                        .lastName(userRedis.getLastName())
                        .name(userRedis.getName())
                        .build());
    }

    @Override
    public Mono<Long> save(User user) {
        var uRedis = UserRedis.builder()
                .userId(user.getId())
                .lastName(user.getLastName())
                .name(user.getName())
                .build();
        return this.reactiveRedisOperations.put("users", user.getId(), uRedis)
                .map(aBoolean -> 1l);
    }

    @Override
    public Mono<Boolean> deleteAll() {
        return reactiveRedisOperations.
                delete("users");
    }
}
