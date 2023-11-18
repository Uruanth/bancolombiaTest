package co.com.onneq.usecase.getuserbyid;

import co.com.onneq.model.user.User;
import co.com.onneq.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetUserByIdUseCase implements Function<String, Mono<User>> {
    private final UserRepository userRepository;

    @Override
    public Mono<User> apply(String idString) {
        var id = Integer.valueOf(idString);
        return userRepository.getById(id);
    }
}
