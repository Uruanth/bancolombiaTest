package co.com.onneq.consumer.user;

import co.com.onneq.model.user.User;
import co.com.onneq.model.user.gateways.ExternalUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserConsumerAdapter implements ExternalUserRepository {

//    private final WebClient webClient;


    @Override
    public Mono<User> getUserById(String id) {
        var webClient = WebClient.create("https://reqres.in/api");
        return webClient.get()
                .uri("/users/{id}", id)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .map(userResponse -> User.builder()
                        .id(userResponse.getData().getId())
                        .name(userResponse.getData().getFirst_name())
                        .lastName(userResponse.getData().getLast_name())
                        .build());
    }
}
