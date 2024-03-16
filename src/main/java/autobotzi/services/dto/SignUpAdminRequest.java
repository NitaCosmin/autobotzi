package autobotzi.services.dto;

import autobotzi.dto.SignUpRequest;
import autobotzi.organizations.util.OrganizationsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SignUpAdminRequest {

    private SignUpRequest signUpRequest;
    private OrganizationsDto organizationsDto;


}