package autobotzi.user;

import autobotzi.user.dto.*;
import autobotzi.user.utils.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;


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
}
