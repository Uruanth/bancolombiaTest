package co.com.onneq.consumer.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private UserData data;
    private Support support;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class UserData{
        private Integer id;
        private String email;
        private String first_name;
        private String last_name;
        private String avatar;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Support {
        private String url;
        private String text;
    }

}



