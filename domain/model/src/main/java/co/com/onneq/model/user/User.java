package co.com.onneq.model.user;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {

    private Integer id;
    private String name;
    private String lastName;

}
