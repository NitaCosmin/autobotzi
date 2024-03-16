package autobotzi.services;

import autobotzi.dto.JwtAuthenticationResponse;
import autobotzi.dto.RefreshTokenRequest;
import autobotzi.dto.SignInRequest;
import autobotzi.dto.SignUpRequest;
import autobotzi.organizations.util.OrganizationsDto;
import autobotzi.services.dto.SignUpAdminRequest;
import autobotzi.user.Users;

public interface AuthenticationService {
    Users signUpUser(SignUpRequest user, String adminEmail);


    Users SignUpAdmin(SignUpAdminRequest signUpAdminRequest);
    JwtAuthenticationResponse signIn(SignInRequest signInRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    Users resetPassword(String email, String newPassword);
}
