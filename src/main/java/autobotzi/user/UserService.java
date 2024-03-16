package autobotzi.user;

import autobotzi.user.dto.*;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();

    List<UsersDto> getAll();

    List<UsersAdminViewDto> getAllAdminView();

    List<UsersOrganizationsDto> getAllUsersWithOrganizations();

    List<UsersPreViewDto> getAllPreView();
    List<UsersDto> getUsersByRole(String role);
    UsersDto updateUserRole(String email, String role);
    UsersDto getUserByEmail(String email);
    UsersDto updateUserByEmail(String email, String name);
    List<UsersDto> getUsersByDepartment(String departmentName);
}
