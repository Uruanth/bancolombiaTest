package co.com.onneq.r2dbc.user;

import co.com.onneq.Error.UserError;
import co.com.onneq.model.user.User;
import co.com.onneq.model.user.gateways.UserRepository;
import co.com.onneq.r2dbc.helper.ReactiveAdapterOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserDataAdapter extends ReactiveAdapterOperations<User, UserData, Integer, UserDataRepository>
        implements UserRepository {
    public UserDataAdapter(UserDataRepository repository, UserMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, User.UserBuilder.class).build());
    }

    @Override
    public Mono<User> getById(Integer id) {
        return this.findById(id);
    }

    @Override
    public Mono<User> saveUser(User user) {
        return repository.existsById(user.getId())
                .flatMap(aBoolean ->
                        aBoolean ? Mono.error(new UserError("User with id: " + user.getId() + " already exist"))
                                : Mono.just(user)
                )
                .map(this::toData)
                .flatMap(repository::insertUser)
                .then(Mono.just(user));
    }


}