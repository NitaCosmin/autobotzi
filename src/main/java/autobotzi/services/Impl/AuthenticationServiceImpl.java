package autobotzi.services.Impl;

import autobotzi.dto.JwtAuthenticationResponse;
import autobotzi.dto.RefreshTokenRequest;
import autobotzi.dto.SignInRequest;
import autobotzi.dto.SignUpRequest;
import autobotzi.organizations.Organizations;
import autobotzi.organizations.OrganizationsRepository;
import autobotzi.organizations.util.OrganizationsDto;
import autobotzi.services.dto.SignUpAdminRequest;
import autobotzi.user.utils.Role;
import autobotzi.user.Users;
import autobotzi.user.UserRepository;
import autobotzi.services.JwtService;
import autobotzi.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import static autobotzi.organizations.util.OrganizationsMapper.mapToEntity;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public Users signUpUser(SignUpRequest user, String adminEmail) {
        return userRepository.save(Users.builder()
                .email(user.getEmail())
                .name(user.getName())
                .role(Role.USER)
                .password(passwordEncoder.encode(user.getPassword()))
                .organization(userRepository.findByEmail(adminEmail)
                        .filter(admin -> admin.getRole() == Role.ADMIN)
                        .orElseThrow(() -> new IllegalArgumentException("Admin not found"))
                        .getOrganization())
                .build());
    }
    public Users resetPassword(String email, String newPassword) {
        return userRepository.save(userRepository.findByEmail(email)
                .map(user -> {
                    user.setPassword(passwordEncoder.encode(newPassword));
                    return user;
                })
                .orElseThrow(() -> new IllegalArgumentException("User not found")));
    }
    public Users SignUpAdmin(SignUpAdminRequest signUpAdminRequest) {
        return userRepository.save(Users.builder()
                .email(signUpAdminRequest.getSignUpRequest().getEmail())
                .name(signUpAdminRequest.getSignUpRequest().getName())
                .role(Role.ADMIN)
                .password(passwordEncoder.encode(signUpAdminRequest.getSignUpRequest().getPassword()))
                .organization(mapToEntity(signUpAdminRequest.getOrganizationsDto()))
                .build());

    }

    public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),
                signInRequest.getPassword()));
        var user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow
                (()-> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;

    }
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUsername(refreshTokenRequest.getToken());
        Users user = userRepository.findByEmail(userEmail).orElseThrow();

        if (jwtService.isTokenValid(refreshTokenRequest.getToken(),user)) {
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }

}
