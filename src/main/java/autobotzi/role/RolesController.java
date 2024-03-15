package autobotzi.role;

import autobotzi.role.dto.RolesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RolesController {

    private final RolesService rolesService;

    @GetMapping("/all")
    public List<RolesDto> getAllRoles() {
        return rolesService.getRoles();
    }

    @PostMapping("/add")
    public Roles addRole(RolesDto rolesDto) {
        return rolesService.addRole(rolesDto);
    }

    @PutMapping("/update")
    public void updateRole(RolesDto rolesDto, String name) {
        rolesService.updateRole(rolesDto, name);
    }

    @DeleteMapping("/delete")
    public void deleteRole(String name) {
        rolesService.deleteRole(name);
    }
}
