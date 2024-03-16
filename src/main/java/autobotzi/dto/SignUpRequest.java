package autobotzi.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data

public class SignUpRequest {

    private String name;

    private String password;


    private String email;

}
