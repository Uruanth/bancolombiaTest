package co.com.onneq.api;

import co.com.onneq.Error.UserError;
import co.com.onneq.model.user.User;
import co.com.onneq.usecase.getuserbyid.GetUserByIdUseCase;
import co.com.onneq.usecase.saveuser.SaveUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

    private final GetUserByIdUseCase getUserByIdUseCase;
    private final SaveUserUseCase saveUserUseCase;

    public Mono<ServerResponse> listenGETUseCase(ServerRequest serverRequest) {
        return Mono.defer(() -> Mono.just(serverRequest.pathVariable("id")))
                .flatMap(getUserByIdUseCase)
                .flatMap(user -> ServerResponse.ok()
                        .bodyValue(user)
                );
    }

    public Mono<ServerResponse> listenGETOtherUseCase(ServerRequest serverRequest) {
        return ServerResponse.ok().bodyValue("");
    }

    public Mono<ServerResponse> listenPOSTUseCase(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(User.class)
                .flatMap(saveUserUseCase)
                .flatMap(user -> ServerResponse
                        .ok()
                        .bodyValue(user)
                )
                .onErrorResume(UserError.class, userError -> ServerResponse
                        .status(HttpStatusCode.valueOf(409))
                        .bodyValue(userError.getMessage())
                );
    }
}
