package autobotzi.user;

import autobotzi.user.dto.*;
import autobotzi.user.utils.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();

    List<UsersDto> getAll();

    List<UsersAdminViewDto> getAllAdminView();

    List<UsersOrganizationsDto> getAllUsersWithOrganizations();

    List<UsersPreViewDto> getAllPreView();
    List<UsersDto> getUsersByRole(Role role);
    List<Role> getAllRoles();
    UsersDto updateUserRole(String email, Role role);
    UsersDto getUserByEmail(String email);
    UsersDto updateUserByEmail(String email, String name);
    List<UsersDto> getUsersByDepartment(String departmentName);
    Users deleteUserByEmail(String email);
    void deleteUserFromEverywhere(String email);
}
