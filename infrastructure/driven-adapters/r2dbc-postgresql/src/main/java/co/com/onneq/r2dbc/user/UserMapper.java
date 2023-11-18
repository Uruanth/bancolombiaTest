package co.com.onneq.r2dbc.user;


import co.com.onneq.model.user.User;
import org.reactivecommons.utils.ObjectMapper;

public class UserMapper implements ObjectMapper {
    @Override
    public <T> T map(Object src, Class<T> target) {
         var  userData = ((User) src);
         var user = new UserData();
         user.setUserId(userData.getId());
         user.setName(userData.getName());
         user.setLastName(userData.getLastName());
        return (T) user;
    }

    @Override
    public <T> T mapBuilder(Object src, Class<T> target) {
        var  userData = ((UserData) src);
        return (T) User.builder()
                .id(userData.getUserId())
                .name(userData.getName())
                .lastName(userData.getLastName());
    }
}