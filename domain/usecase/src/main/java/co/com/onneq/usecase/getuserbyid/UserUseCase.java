package co.com.onneq.usecase.getuserbyid;

import co.com.onneq.model.user.User;
import co.com.onneq.model.user.gateways.UserCacheRepository;
import co.com.onneq.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository userRepository;
    private final UserCacheRepository userCacheRepository;

    public Mono<User> getById(String idString) {
        var id = Integer.valueOf(idString);
        userCacheRepository.getById(id).subscribe();
        return userRepository.getById(id);
    }

    public Flux<User> getByName(String name) {
        return userRepository.getByName(name);
    }

    public Flux<User> getAll() {
        return userCacheRepository.getAll()
                .switchIfEmpty(userRepository.getAll()
                        .map(user -> {
                            userCacheRepository.save(user).subscribe();
                            return user;
                        })
                );
    }
}
