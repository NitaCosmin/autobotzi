package autobotzi.user.role.impl;

import autobotzi.role.RolesRepository;
import autobotzi.user.UserRepository;
import autobotzi.user.role.UserRoles;
import autobotzi.user.role.UserRolesRepository;
import autobotzi.user.role.UserRolesService;
import autobotzi.user.role.dto.UsersRolesDto;
import autobotzi.user.role.dto.UsersRolesUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserRolesServiceImpl implements UserRolesService {

    private final UserRolesRepository userRolesRepository;
    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;

    public UserRoles assignRoleToUser(UsersRolesDto usersRolesDto) {
        return userRolesRepository.save(UserRoles.builder()
                .user(userRepository.findByEmail(usersRolesDto.getEmail())
                        .orElseThrow(() -> new IllegalArgumentException("User not found")))
                .role(rolesRepository.findByName(usersRolesDto.getRole())
                        .orElseThrow(() -> new IllegalArgumentException("Role not found")))
                .build());
    }

    public List<UsersRolesDto> getUsersRoles() {
        return userRolesRepository.findAll().stream()
                .map(usersRoles -> UsersRolesDto.builder()
                        .email(usersRoles.getUser().getEmail())
                        .role(usersRoles.getRole().getName())
                        .build())
                .collect(Collectors.toList());
    }

    public List<UsersRolesDto> getUsersRolesByRole(String roleName) {
        return userRolesRepository.findAllByRoleName(roleName).stream()
                .map(usersRoles -> UsersRolesDto.builder()
                        .email(usersRoles.getUser().getEmail())
                        .role(usersRoles.getRole().getName())
                        .build())
                .collect(Collectors.toList());
    }

    public Integer getUsersRolesCount(String roleName) {
        return userRolesRepository.findAllByRoleName(roleName).size();
    }

    public void deleteUsersRole(UsersRolesDto usersRolesDto) {
        userRolesRepository.delete(UserRoles.builder()
                .user(userRepository.findByEmail(usersRolesDto.getEmail())
                        .orElseThrow(() -> new IllegalArgumentException("User not found")))
                .role(rolesRepository.findByName(usersRolesDto.getRole())
                        .orElseThrow(() -> new IllegalArgumentException("Role not found")))
                .build());
    }

    public UserRoles updateUsersRoleByUserEmail(UsersRolesUpdate usersRolesUpdate) {
        return userRolesRepository.save(UserRoles.builder()
                .user(userRepository.findByEmail(usersRolesUpdate.getUser())
                        .orElseThrow(() -> new IllegalArgumentException("User not found")))
                .role(rolesRepository.findByName(usersRolesUpdate.getRole())
                        .orElseThrow(() -> new IllegalArgumentException("Role not found")))
                .build());
    }
}
