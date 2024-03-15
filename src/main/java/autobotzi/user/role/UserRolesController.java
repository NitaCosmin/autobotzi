package autobotzi.user.role;

import autobotzi.user.role.dto.UsersRolesDto;
import autobotzi.user.role.dto.UsersRolesUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-roles")
@RequiredArgsConstructor
public class UserRolesController {

    private final UserRolesService userRolesService;
    @PostMapping("/assign")
    public UserRoles assignRoleToUser(@RequestBody UsersRolesDto usersRolesDto) {
        return userRolesService.assignRoleToUser(usersRolesDto);
    }
    @GetMapping("/all")
    public List<UsersRolesDto> getAllUsersRoles() {
        return userRolesService.getUsersRoles();
    }
    @GetMapping("/by-role")
    public List<UsersRolesDto> getUsersRolesByRole(String roleName) {
        return userRolesService.getUsersRolesByRole(roleName);
    }
    @GetMapping("/count")
    public Integer getUsersRolesCount(String roleName) {
        return userRolesService.getUsersRolesCount(roleName);
    }
    @PutMapping("/update")
    public UserRoles updateUsersRoleByUserEmail(@RequestBody UsersRolesUpdate usersRolesUpdate, String email) {
        return userRolesService.updateUsersRoleByUserEmail(usersRolesUpdate, email);
    }
    @DeleteMapping("/delete")
    public void deleteUsersRole(String email) {
        userRolesService.deleteUsersRole(email);
    }

}
