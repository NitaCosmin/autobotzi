package autobotzi.user;

import autobotzi.user.dto.UsersAdminViewDto;
import autobotzi.user.dto.UsersDto;
import autobotzi.user.dto.UsersOrganizationsDto;
import autobotzi.user.dto.UsersPreViewDto;
import autobotzi.user.role.UserRoles;
import autobotzi.user.role.UserRolesService;
import autobotzi.user.role.dto.UsersRolesDto;
import autobotzi.user.role.dto.UsersRolesUpdate;
import autobotzi.user.skill.UserSkills;
import autobotzi.user.skill.UserSkillsService;
import autobotzi.user.skill.dto.UserSkillsAssign;
import autobotzi.user.skill.dto.UserSkillsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;
    private final UserSkillsService userSkillsService;


    @GetMapping
    public ResponseEntity<String> getUser() {
        return ResponseEntity.ok("Hello User");
    }

    @GetMapping("/all")
    public List<UsersDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/all-admin-view")
    public List<UsersAdminViewDto> getAllAdminView() {
        return userService.getAllAdminView();
    }

    @GetMapping("/all-users-with-organizations")
    public List<UsersOrganizationsDto> getAllUsersWithOrganizations() {
        return userService.getAllUsersWithOrganizations();
    }

    @GetMapping("/all-preview")
    public List<UsersPreViewDto> getAllPreview() {
        return userService.getAllPreView();
    }

    @GetMapping("/role")
    public List<UsersDto> getByRole(@RequestParam String role) {
        return userService.getUsersByRole(role);
    }

    @GetMapping("/get-by-email")
    public UsersDto getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/get-by-department")
    public List<UsersDto> getByDepartment(@RequestParam String departmentName) {
        return userService.getUsersByDepartment(departmentName);
    }

    @PutMapping("/update-role")
    public UsersDto updateUserRole(@RequestParam String email, @RequestParam String role) {
        return userService.updateUserRole(email, role);
    }

    @PutMapping("/update-by-email")
    public UsersDto updateUserByEmail(@RequestParam String email, @RequestParam String name) {
        return userService.updateUserByEmail(email, name);
    }
    //===========================================================
    //User Skills
    //===========================================================

    @GetMapping("/skills")
    public List<UserSkillsDto> getAllUserSkills() {
        return userSkillsService.getAllUserSkills();
    }

    @PutMapping("/skills")
    public UserSkills addSkillToUser(@RequestBody UserSkillsAssign userSkillsAssign) {
        return userSkillsService.addSkillToUser(userSkillsAssign);
    }

    @PutMapping("/skills/update")
    public UserSkills updateSkillsByUserEmail(@AuthenticationPrincipal UserDetails userDetails, @RequestBody UserSkillsDto userSkills) {
        String email = userDetails.getUsername();
        return userSkillsService.updateSkillsByUserEmail(email, userSkills);
    }

    @PostMapping("/skills/validate")
    public UserSkills addValidationToUserSkill(@RequestParam String email) {
        return userSkillsService.addValidationToUserSkill(email);
    }

    @GetMapping("/skills/validated")
    public List<UserSkillsDto> getAllValidatedSkills() {
        return userSkillsService.getAllValidatedSkills();
    }

    @GetMapping("/skills/non-validated")
    public List<UserSkillsDto> getAllNonValidatedSkills() {
        return userSkillsService.getAllNonValidatedSkills();
    }

    //===========================================================
    //User Roles
    //===========================================================
    private final UserRolesService userRolesService;

    @PostMapping("/roles/assign")
    public UserRoles assignRoleToUser(@RequestBody UsersRolesDto usersRolesDto) {
        return userRolesService.assignRoleToUser(usersRolesDto);
    }

    @GetMapping("/roles/all")
    public List<UsersRolesDto> getAllUsersRoles() {
        return userRolesService.getUsersRoles();
    }

    @GetMapping("/roles/by-role")
    public List<UsersRolesDto> getUsersRolesByRole(String roleName) {
        return userRolesService.getUsersRolesByRole(roleName);
    }

    @GetMapping("/roles/count")
    public Integer getUsersRolesCount(String roleName) {
        return userRolesService.getUsersRolesCount(roleName);
    }

    @PutMapping("/roles/update")
    public UserRoles updateUsersRoleByUserEmail(@RequestBody UsersRolesUpdate usersRolesUpdate) {
        return userRolesService.updateUsersRoleByUserEmail(usersRolesUpdate);
    }

    @DeleteMapping("/roles/delete")
    public void deleteUsersRole(@RequestBody UsersRolesDto usersRolesDto) {
        userRolesService.deleteUsersRole(usersRolesDto);
    }

}
