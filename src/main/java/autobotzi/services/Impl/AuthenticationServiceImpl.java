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
    private final OrganizationsRepository organizationsRepository;
    public Users signUpUser(SignUpRequest user, String adminEmail) {

        Users admin = userRepository.findByEmail(adminEmail).orElseThrow(() -> new IllegalArgumentException("Admin not found"));

        if (admin.getRole() != Role.ADMIN) {
            throw new IllegalArgumentException("Provided user is not an admin");
        }

        Users newUser = new Users();
        newUser.setEmail(user.getEmail());
        newUser.setName(user.getName());
        newUser.setRole(Role.USER);
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setOrganization(admin.getOrganization());

        return userRepository.save(newUser);
    }
    public Users resetPassword(String email, String newPassword) {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setPassword(passwordEncoder.encode(newPassword));

        return userRepository.save(user);
    }
    public Users SignUpAdmin(SignUpAdminRequest signUpAdminRequest) {
        Users newAdmin = new Users();
        newAdmin.setEmail(signUpAdminRequest.getSignUpRequest().getEmail());
        newAdmin.setName(signUpAdminRequest.getSignUpRequest().getName());
        newAdmin.setRole(Role.ADMIN);
        newAdmin.setPassword(passwordEncoder.encode(signUpAdminRequest.getSignUpRequest().getPassword()));
        Organizations organization = mapToEntity(signUpAdminRequest.getOrganizationsDto());
        newAdmin.setOrganization(organization);

        organizationsRepository.save(organization);
        return userRepository.save(newAdmin);

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
