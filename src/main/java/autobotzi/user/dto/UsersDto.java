package autobotzi.user.dto;

import autobotzi.user.utils.Role;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class UsersDto {

    private String name;

    @Email
    private String email;

    private Role role;


}
