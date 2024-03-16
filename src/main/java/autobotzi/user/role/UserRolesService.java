package autobotzi.user.role;

import autobotzi.user.role.dto.UsersRolesDto;
import autobotzi.user.role.dto.UsersRolesUpdate;

import java.util.List;

public interface UserRolesService {
    UserRoles assignRoleToUser(UsersRolesDto usersRolesDto);
    List<UsersRolesDto> getUsersRoles();
    List<UsersRolesDto> getUsersRolesByRole(String roleName);
    Integer getUsersRolesCount(String roleName);
    void deleteUsersRole(UsersRolesDto usersRolesDto);
    UserRoles updateUsersRoleByUserEmail(UsersRolesUpdate usersRolesUpdate);
}
