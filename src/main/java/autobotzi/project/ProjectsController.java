package autobotzi.project;

import autobotzi.project.dto.ProjectUpdate;
import autobotzi.project.dto.ProjectsDateDto;
import autobotzi.project.dto.ProjectsDto;
import autobotzi.project.utils.Period;
import autobotzi.project.utils.Status;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectsController {

    private final ProjectsService projectsService;

    @PostMapping
    public Projects createProject(@AuthenticationPrincipal UserDetails userDetails
            , ProjectsDto projectsDto) {
        String email = userDetails.getUsername();
        return projectsService.createProject(email, projectsDto);
    }
    @PutMapping
    public Projects updateProjectStatus(@AuthenticationPrincipal UserDetails userDetails,@RequestParam String name
            , @RequestBody ProjectUpdate projectUpdate) {
        String email = userDetails.getUsername();
        return projectsService.updateProjectStatusByProjectName(email,name, projectUpdate);
    }
    @PutMapping("/assignPM")
    public Projects addProjectManagerToProject(@AuthenticationPrincipal UserDetails userDetails
            ,@RequestParam String projectManager,@RequestParam String name) {
        String email = userDetails.getUsername();
        return projectsService.addProjectManagerToProjectByEmail(email,projectManager, name);
    }
    @PutMapping("/updateDate")
    public Projects updateProjectDate(@AuthenticationPrincipal UserDetails userDetails
            ,@RequestParam String name, @RequestBody ProjectsDateDto projectsDto) {
        String email = userDetails.getUsername();
        return projectsService.updateProjectDateByProjectName(email,name, projectsDto);
    }
    @GetMapping("/{name}")
    public ProjectsDto findProjectByName(@PathVariable String name) {
        return projectsService.findProjectByName(name);
    }
    @GetMapping
    public List<ProjectsDto> findAllProjects() {
        return projectsService.findAllProjects();
    }
    @GetMapping("/status")
    public List<ProjectsDto> findAllProjectsByStatus(@RequestParam Status status) {
        return projectsService.findAllProjectsByStatus(status);
    }
    @GetMapping("/period")
    public List<ProjectsDto> findAllProjectsByPeriod(@RequestParam Period period) {
        return projectsService.findAllProjectsByPeriod(period);
    }
    @GetMapping("/status/all")
    public List<Status> getAllStatus() {
        return projectsService.getAllStatus();
    }
    @GetMapping("/period/all")
    public List<Period> getAllPeriods() {
        return projectsService.getAllPeriods();
    }
    @DeleteMapping("/")
    public Projects deleteProject(@PathVariable String name) {
        return projectsService.deleteProject(name);
    }

}
