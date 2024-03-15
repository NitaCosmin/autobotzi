package autobotzi.role;

import autobotzi.role.dto.RolesDto;

import java.util.List;

public interface RolesService {
    Roles addRole(RolesDto role);
    List<RolesDto> getRoles();
    void updateRole(RolesDto role, String name);
    void deleteRole(String name);
}
