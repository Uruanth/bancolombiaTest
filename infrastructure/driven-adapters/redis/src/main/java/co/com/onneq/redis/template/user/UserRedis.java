package co.com.onneq.redis.template.user;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("users")
public class UserRedis {
    @Id
    private Integer userId;
    private String name;
    private String lastName;
}

