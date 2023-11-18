package co.com.onneq.r2dbc.user;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserDataRepository extends ReactiveCrudRepository<UserData, Integer>, ReactiveQueryByExampleExecutor<UserData> {
    @Query("INSERT INTO users (user_id, name, last_name) VALUES (:#{#data.userId}, :#{#data.name}, :#{#data.lastName})")
    Mono<Void> insertUser(@Param("data") UserData data);

}
