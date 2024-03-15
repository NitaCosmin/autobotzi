package autobotzi.organizations;

import autobotzi.organizations.util.OrganizationsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/organizations")
public class OrganizationsController {

    private final OrganizationsService organizationsService;
    @GetMapping
    public List<OrganizationsDto> getAllOrganizations() {
        return organizationsService.getAllOrganizations();
    }

    @PostMapping
    public Organizations addOrganization(@RequestBody OrganizationsDto organizationsDto) {
        return organizationsService.addOrganization(organizationsDto);
    }

    @DeleteMapping("/{id}")
    public void deleteOrganization(@PathVariable Long id) {
        organizationsService.deleteOrganization(id);
    }
}
