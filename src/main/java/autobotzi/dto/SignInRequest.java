package autobotzi.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data

public class SignInRequest {

    private String email;
    private String password;


}
