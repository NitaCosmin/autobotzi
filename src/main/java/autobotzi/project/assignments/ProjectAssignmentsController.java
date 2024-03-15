package autobotzi.project.assignments;

import autobotzi.project.assignments.dto.ProjectAssignmentsDto;
import autobotzi.project.assignments.dto.ProjectAssignmentsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project-assignments")
@RequiredArgsConstructor
public class ProjectAssignmentsController {

    private final ProjectAssignmentsService projectAssignmentsService;

    @PostMapping
    public ProjectAssignments assignProjectToUser(@RequestBody ProjectAssignmentsDto projectAssignmentsDto) {
        return projectAssignmentsService.addEmployeeToProject(projectAssignmentsDto);
    }
    @PutMapping
    public ProjectAssignments updateEmployeeRoleInProject(@RequestBody ProjectAssignmentsDto projectAssignmentsDto) {
        return projectAssignmentsService.updateEmployeeRoleInProject(projectAssignmentsDto);
    }
    @GetMapping
    public void getEmployeesByProject(@RequestBody ProjectAssignmentsResponse projectAssignmentsResponse) {
        projectAssignmentsService.getEmployeesByProject(projectAssignmentsResponse);
    }
    @GetMapping("/status")
    public void GetAllStatusAssignments() {
        projectAssignmentsService.GetAllStatusAssignments();
    }
    @DeleteMapping
    public void deleteEmployeeFromProject(@RequestParam String email) {
        projectAssignmentsService.deleteEmployeeFromProject(email);
    }
}
