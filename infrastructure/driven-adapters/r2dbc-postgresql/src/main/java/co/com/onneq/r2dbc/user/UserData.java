package co.com.onneq.r2dbc.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table("users")
public class UserData {
    @Id
    @Column("user_id")
    private Integer userId;
    private String name;
    @Column("last_name")
    private String lastName;
}
