package co.com.onneq.usecase.saveuser;

import co.com.onneq.model.user.User;
import co.com.onneq.model.user.gateways.ExternalUserRepository;
import co.com.onneq.model.user.gateways.UserCacheRepository;
import co.com.onneq.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Log
@RequiredArgsConstructor
public class SaveUserUseCase implements Function<String, Mono<User>> {

    private final UserRepository userRepository;
    private final UserCacheRepository userCacheRepository;
    private final ExternalUserRepository externalUserRepository;

    @Override
    public Mono<User> apply(String id) {

        return externalUserRepository.getUserById(id)
                .flatMap(userRepository::saveUser)
                .map(user -> {
                    userCacheRepository.deleteAll()
                            .subscribe(aBoolean -> log.info("Summary after delete cache: " + aBoolean));
                    return user;
                });
    }
}
