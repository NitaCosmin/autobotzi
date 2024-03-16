package autobotzi.controller;

import autobotzi.dto.JwtAuthenticationResponse;
import autobotzi.dto.RefreshTokenRequest;
import autobotzi.dto.SignInRequest;
import autobotzi.dto.SignUpRequest;
import autobotzi.organizations.util.OrganizationsDto;
import autobotzi.services.dto.SignUpAdminRequest;
import autobotzi.user.Users;
import autobotzi.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/sign-up-admin")
    public Users signUpAdmin(@RequestBody SignUpAdminRequest signUpAdminRequest) {
        return authenticationService.SignUpAdmin(signUpAdminRequest);
    }

    @PostMapping("/reset-password")
    public Users resetPassword(@RequestParam String email, @RequestParam String newPassword) {
        return authenticationService.resetPassword(email, newPassword);
    }
    @PostMapping("/sign-up")
    public Users signUp(@RequestBody SignUpRequest signUpRequest, @RequestParam String adminEmail) {
        return authenticationService.signUpUser(signUpRequest, adminEmail);
    }
    @PostMapping("/sign-in")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));

    }
    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));

    }
}
