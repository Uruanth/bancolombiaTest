package co.com.onneq.consumer;

import co.com.onneq.consumer.user.UserResponse;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ObjectRequest {
    private String val1;
    private String val2;
}
