package autobotzi.organizations;

import autobotzi.organizations.util.OrganizationsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/organizations")
@CrossOrigin(origins = {"http://localhost:3000"
        ,"http://atc-2024-autobotzi-fe-linux-web-app.azurewebsites.net/"
        ,"https://frontend-jf48yfydc-eduard-ionel-eduards-projects.vercel.app/"
        ,"https://front-autobotzi-c55123365842.herokuapp.com/"})
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
