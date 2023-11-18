package co.com.onneq.usecase.getuserbyid;

import co.com.onneq.model.user.User;
import co.com.onneq.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class UserUseCase{
    private final UserRepository userRepository;

    public Mono<User> getById(String idString) {
        var id = Integer.valueOf(idString);
        return userRepository.getById(id);
    }

    public Flux<User> getByName(String name) {
        return userRepository.getByName(name);
    }

    public Flux<User> getAll(){
        return userRepository.getAll();
    }
}
