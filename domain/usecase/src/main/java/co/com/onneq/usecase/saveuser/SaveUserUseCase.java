package co.com.onneq.usecase.saveuser;

import co.com.onneq.model.user.User;
import co.com.onneq.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class SaveUserUseCase implements Function<User, Mono<User>> {
    private final UserRepository userRepository;

    @Override
    public Mono<User> apply(User user) {
        return userRepository.saveUser(user);
    }
}
