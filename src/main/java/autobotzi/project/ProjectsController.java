package autobotzi.project;

import autobotzi.project.assignments.ProjectAssignments;
import autobotzi.project.assignments.ProjectAssignmentsService;
import autobotzi.project.assignments.dto.ProjectAssignmentsDto;
import autobotzi.project.assignments.dto.ProjectAssignmentsResponse;
import autobotzi.project.dto.ProjectUpdate;
import autobotzi.project.dto.ProjectsDateDto;
import autobotzi.project.dto.ProjectsDto;
import autobotzi.project.skill.ProjectSkills;
import autobotzi.project.skill.ProjectSkillsService;
import autobotzi.project.skill.dto.ProjectSkillsDto;
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
    private final ProjectSkillsService projectSkillsService;
    private final ProjectAssignmentsService projectAssignmentsService;

    @PostMapping
    public Projects createProject(@AuthenticationPrincipal UserDetails userDetails
            , ProjectsDto projectsDto) {
        String email = userDetails.getUsername();
        return projectsService.createProject(email, projectsDto);
    }

    @PutMapping
    public Projects updateProjectStatus(@AuthenticationPrincipal UserDetails userDetails, @RequestParam String name
            , @RequestBody ProjectUpdate projectUpdate) {
        return projectsService.updateProjectStatusByProjectName(userDetails.getUsername(), name, projectUpdate);
    }

    @PutMapping("/assignPM")
    public Projects addProjectManagerToProject(@AuthenticationPrincipal UserDetails userDetails
            , @RequestParam String projectManager, @RequestParam String name) {
        String email = userDetails.getUsername();
        return projectsService.addProjectManagerToProjectByEmail(email, projectManager, name);
    }

    @PutMapping("/updateDate")
    public Projects updateProjectDate(@AuthenticationPrincipal UserDetails userDetails
            , @RequestParam String name, @RequestBody ProjectsDateDto projectsDto) {
        String email = userDetails.getUsername();
        return projectsService.updateProjectDateByProjectName(email, name, projectsDto);
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

    @DeleteMapping
    public Projects deleteProject(@PathVariable String name) {
        return projectsService.deleteProject(name);
    }


    //==========================================================
    //Project Skills
    //==========================================================
    @PostMapping("/skills")
    public ProjectSkills addSkillToProject(@RequestBody ProjectSkillsDto projectSkillsDto) {
        return projectSkillsService.addSkillToProject(projectSkillsDto);
    }

    @PutMapping("/skills")
    public ProjectSkills updateSkillInProject(@RequestBody ProjectSkillsDto projectSkillsDto) {
        return projectSkillsService.updateSkillInProject(projectSkillsDto);
    }

    @GetMapping("/skills")
    public void getSkillsByProject(@RequestBody ProjectSkillsDto projectSkillsDto) {
        projectSkillsService.getSkillsByProject(projectSkillsDto);
    }

    @DeleteMapping("/skills")
    public void deleteSkillFromProject(@RequestParam String skills) {
        projectSkillsService.deleteSkillFromProject(skills);
    }

    //==========================================================
    //Project Assignments
    //==========================================================


    @PostMapping("/assignments")
    public ProjectAssignments assignProjectToUser(@RequestBody ProjectAssignmentsDto projectAssignmentsDto) {
        return projectAssignmentsService.addEmployeeToProject(projectAssignmentsDto);
    }

    @PutMapping("/assignments")
    public ProjectAssignments updateEmployeeRoleInProject(@RequestBody ProjectAssignmentsDto projectAssignmentsDto) {
        return projectAssignmentsService.updateEmployeeRoleInProject(projectAssignmentsDto);
    }

    @GetMapping("/assignments")
    public void getEmployeesByProject(@RequestBody ProjectAssignmentsResponse projectAssignmentsResponse) {
        projectAssignmentsService.getEmployeesByProject(projectAssignmentsResponse);
    }

    @GetMapping("/assignments/status")
    public void GetAllStatusAssignments() {
        projectAssignmentsService.GetAllStatusAssignments();
    }

    @DeleteMapping("/assignments")
    public void deleteEmployeeFromProject(@RequestParam String email) {
        projectAssignmentsService.deleteEmployeeFromProject(email);
    }
}
