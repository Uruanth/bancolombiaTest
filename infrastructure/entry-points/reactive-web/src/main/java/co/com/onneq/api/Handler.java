package co.com.onneq.api;

import co.com.onneq.Error.UserError;
import co.com.onneq.model.user.User;
import co.com.onneq.usecase.getuserbyid.UserUseCase;
import co.com.onneq.usecase.saveuser.SaveUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.TemporalUnit;

@Component
@RequiredArgsConstructor
public class Handler {

    private final UserUseCase userUseCase;
    private final SaveUserUseCase saveUserUseCase;

    public Mono<ServerResponse> listenGETUseCase(ServerRequest serverRequest) {
        return Mono.defer(() -> Mono.just(serverRequest.pathVariable("id")))
                .flatMap(userUseCase::getById)
                .flatMap(user -> ServerResponse.ok()
                        .bodyValue(user)
                );
    }

    public Mono<ServerResponse> listenGETOtherUseCase(ServerRequest serverRequest) {
        return ServerResponse.ok().bodyValue("");
    }

    public Mono<ServerResponse> listenPOSTUseCase(ServerRequest serverRequest) {
        return Mono.defer(() -> Mono.just(serverRequest.pathVariable("id")))
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

    public Mono<ServerResponse> findUserByNameListener(ServerRequest serverRequest) {
        return Mono.defer(() -> Mono.just(serverRequest.queryParam("name")
                        .orElseThrow(() -> new RuntimeException("Query param error")))
                )
                .map(userUseCase::getByName)
                .delayElement(Duration.ofMillis(1500))
                .flatMap(user -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_EVENT_STREAM)
                        .body(user, User.class)
                )
                .onErrorResume(throwable -> ServerResponse.badRequest()
                        .bodyValue(throwable.getMessage()));
    }

    public Mono<ServerResponse> findAllUserListener(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(userUseCase.getAll()
                        .delayElements(Duration.ofMillis(1200)), User.class)
                .onErrorResume(throwable -> ServerResponse.badRequest()
                        .bodyValue(throwable.getMessage()));
    }


}
