package co.com.onneq.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(GET("/api/user/{id}"), handler::listenGETUseCase)
                .andRoute(GET("/api/users/find"), handler::findUserByNameListener)
                .andRoute(GET("/api/users/find/all"), handler::findAllUserListener)
                .andRoute(POST("/api/user/{id}"), handler::listenPOSTUseCase)
                .and(route(GET("/api/otherusercase/path"), handler::listenGETOtherUseCase));
    }
}
