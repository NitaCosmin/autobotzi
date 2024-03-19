package autobotzi.project.assignments.impl;

import autobotzi.project.ProjectsRepository;
import autobotzi.project.assignments.ProjectAssignments;
import autobotzi.project.assignments.ProjectAssignmentsRepository;
import autobotzi.project.assignments.ProjectAssignmentsService;
import autobotzi.project.assignments.dto.ProjectAssignmentsDto;
import autobotzi.project.assignments.dto.ProjectAssignmentsResponse;
import autobotzi.project.assignments.utils.StatusAssignments;
import autobotzi.role.RolesRepository;
import autobotzi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor



public class ProjectAssignmentsServiceImpl implements ProjectAssignmentsService {

    private final ProjectAssignmentsRepository projectAssignmentsRepository;
    private final UserRepository userRepository;
    private final ProjectsRepository projectsRepository;
    private final RolesRepository roleRepository;

    public ProjectAssignments addEmployeeToProject(ProjectAssignmentsDto projectAssignmentsDto) {

        return projectAssignmentsRepository.save(userRepository
                .findByEmail(projectAssignmentsDto.getUser())
                .map(user -> ProjectAssignments.builder()
                        .user(user)
                        .project(projectsRepository.findByName(projectAssignmentsDto.getProject())
                                .orElseThrow(() -> new IllegalArgumentException("Project not found")))
                        .role(roleRepository.findByName(projectAssignmentsDto.getRole())
                                .orElseThrow(() -> new IllegalArgumentException("Role not found")))
                        .build())
                .orElseThrow(() -> new IllegalArgumentException("User not found")
                ));
    }
    public ProjectAssignments updateEmployeeRoleInProject(ProjectAssignmentsDto projectAssignmentsDto) {

        return projectAssignmentsRepository.save(userRepository
                .findByEmail(projectAssignmentsDto.getUser())
                .map(user -> projectAssignmentsRepository.findByUser(user)
                        .map(projectAssignments -> ProjectAssignments.builder()
                                .user(user)
                                .project(projectsRepository.findByName(projectAssignmentsDto.getProject())
                                        .orElseThrow(() -> new IllegalArgumentException("Project not found")))
                                .role(roleRepository.findByName(projectAssignmentsDto.getRole())
                                        .orElseThrow(() -> new IllegalArgumentException("Role not found")))
                                .build())
                        .orElseThrow(() -> new IllegalArgumentException("Project assignment not found")))
                .orElseThrow(() -> new IllegalArgumentException("User not found")
                ));
    }
    public List<ProjectAssignmentsDto> getEmployeesByProject(ProjectAssignmentsResponse projectAssignmentsResponse) {

        return projectAssignmentsRepository.findAll().stream()
                .filter(projectAssignments -> projectAssignments.getProject().getName().equals(projectAssignmentsResponse.getProject()))
                .map(projectAssignments -> ProjectAssignmentsDto.builder()
                        .user(projectAssignments.getUser().getEmail())
                        .role(projectAssignments.getRole().getName())
                        .project(projectAssignments.getProject().getName())
                        .build())
                .collect(Collectors.toList());
    }
    public List<StatusAssignments> GetAllStatusAssignments()
    {
        return List.of(StatusAssignments.values());
    }
    public void deleteEmployeeFromProject(String email) {
        projectAssignmentsRepository.delete(
                userRepository.findByEmail(email)
                        .map(user -> projectAssignmentsRepository.findByUser(user)
                                .orElseThrow(() -> new IllegalArgumentException("Project assignment not found")))
                        .orElseThrow(() -> new IllegalArgumentException("User not found")
                        ));
    }


}
