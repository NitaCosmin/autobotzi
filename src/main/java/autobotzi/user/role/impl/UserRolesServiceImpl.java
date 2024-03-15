package autobotzi.user.role.impl;

import autobotzi.role.Roles;
import autobotzi.role.RolesRepository;
import autobotzi.user.UserRepository;
import autobotzi.user.Users;
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
        Users user = userRepository.findByEmail(usersRolesDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Roles role = rolesRepository.findByName(usersRolesDto.getRole())
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        UserRoles usersRoles = new UserRoles();
        usersRoles.setUser(user);
        usersRoles.setRole(role);

        return userRolesRepository.save(usersRoles);
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

    public void deleteUsersRole(String email) {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<UserRoles> usersRolesList = userRolesRepository.findByUser(user);
        userRolesRepository.deleteAll(usersRolesList);
    }

    public UserRoles updateUsersRoleByUserEmail(UsersRolesUpdate usersRolesUpdate, String email) {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Roles role = rolesRepository.findByName(usersRolesUpdate.getRole())
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        UserRoles usersRoles = userRolesRepository.findByUser(user).stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User role not found"));
        usersRoles.setRole(role);
        usersRoles.setUser(user);

        return userRolesRepository.save(usersRoles);
    }
}
