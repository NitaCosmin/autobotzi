package autobotzi.project.assignments;

import autobotzi.project.assignments.dto.ProjectAssignmentsDto;
import autobotzi.project.assignments.dto.ProjectAssignmentsResponse;
import autobotzi.project.assignments.utils.StatusAssignments;

import java.util.List;

public interface ProjectAssignmentsService {
    ProjectAssignments addEmployeeToProject(ProjectAssignmentsDto projectAssignmentsDto);
    ProjectAssignments updateEmployeeRoleInProject(ProjectAssignmentsDto projectAssignmentsDto);
    List<ProjectAssignmentsDto> getEmployeesByProject(ProjectAssignmentsResponse projectAssignmentsResponse);
    List<StatusAssignments> GetAllStatusAssignments();
    void deleteEmployeeFromProject(String email);
}
