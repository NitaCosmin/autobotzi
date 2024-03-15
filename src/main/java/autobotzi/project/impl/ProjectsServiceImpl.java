package autobotzi.project.impl;

import autobotzi.project.Projects;
import autobotzi.project.ProjectsRepository;
import autobotzi.project.ProjectsService;
import autobotzi.project.dto.ProjectUpdate;
import autobotzi.project.dto.ProjectsDateDto;
import autobotzi.project.dto.ProjectsDto;
import autobotzi.project.utils.Period;
import autobotzi.project.utils.Status;
import autobotzi.role.Roles;
import autobotzi.role.RolesRepository;
import autobotzi.user.UserRepository;
import autobotzi.user.Users;
import autobotzi.user.utils.Role;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ProjectsServiceImpl implements ProjectsService {

    private final ProjectsRepository projectsRepository;
    private final UserRepository userRepository;
    private final RolesRepository roleRepository;

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public Projects createProject(String email, ProjectsDto projectsDto) {
        Users users = userRepository.findByEmail(email).orElseThrow(()
                -> new RuntimeException("User not found"));
        if (projectsRepository.findByName(projectsDto.getName()).isPresent()) {
            throw new RuntimeException("A project with the name " + projectsDto.getName() + " already exists");
        }
        Projects projects = Projects.builder()
                .name(projectsDto.getName())
                .description(projectsDto.getDescription())
                .period(projectsDto.getPeriod())
                .projectStatus(projectsDto.getProjectStatus())
                .startDate(projectsDto.getStartDate())
                .deadline(projectsDto.getDeadLine())
                .technology(projectsDto.getTechnology())
                .organization(users.getOrganization())
                .build();
        projectsRepository.save(projects);
        return projects;
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN') or hasRole('PROJECT_MANAGER')")
    public Projects updateProjectStatusByProjectName(String email, String name, ProjectUpdate projectUpdateDto) {
        return projectsRepository.save(
                userRepository.findByEmail(email)
                        .map(user -> projectsRepository.findByName(name)
                                .map(projects -> {
                                    projects.setProjectStatus(projectUpdateDto.getStatus());
                                    projects.setName(projectUpdateDto.getName());
                                    projects.setDescription(projectUpdateDto.getDescription());
                                    projects.setPeriod(projectUpdateDto.getPeriod());
                                    return projects;
                                })
                                .orElseThrow(() -> new RuntimeException("Project not found"))
                        )
                        .orElseThrow(() -> new RuntimeException("User not found")));
    }


    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public Projects addProjectManagerToProjectByEmail(String admin, String email, String name) {
        Users projectManager = userRepository.findByEmail(email).orElseThrow(()
                -> new RuntimeException("Project Manager not found"));
        Projects projects = projectsRepository.findByName(name).orElseThrow();
        Roles projectManagerRole = roleRepository.findByName("PROJECT_MANAGER")
                .orElseThrow(() -> new RuntimeException("Role not found"));
        projectManager.setRole(Role.valueOf(projectManagerRole.getName()));
        userRepository.save(projectManager);
        projects.setUser(projectManager);
        projectsRepository.save(projects);
        return projects;
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public Projects updateProjectDateByProjectName(String email, String name, ProjectsDateDto projectsDto) {
        Users users = userRepository.findByEmail(email).orElseThrow(()
                -> new RuntimeException("User not found"));
        Projects projects = projectsRepository.findByName(name).orElseThrow();

        if (!projects.getOrganization().equals(users.getOrganization())) {
            throw new RuntimeException
                    ("User does not belong to the same organization as the project");
        }

        projects.setStartDate(projectsDto.getStartDate());
        projects.setDeadline(projectsDto.getDeadLine());

        projectsRepository.save(projects);
        return projects;
    }

    @Transactional
    public ProjectsDto findProjectByName(String name) {
        return projectsRepository.findByName(name)
                .map(project -> ProjectsDto.builder()
                        .name(project.getName())
                        .description(project.getDescription())
                        .period(project.getPeriod())
                        .projectStatus(project.getProjectStatus())
                        .startDate(project.getStartDate())
                        .deadLine(project.getDeadline())
                        .technology(project.getTechnology())
                        .build())
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    @Transactional
    public List<ProjectsDto> findAllProjects() {

        return projectsRepository.findAll().stream()
                .map(project -> ProjectsDto.builder()
                        .name(project.getName())
                        .description(project.getDescription())
                        .period(project.getPeriod())
                        .projectStatus(project.getProjectStatus())
                        .startDate(project.getStartDate())
                        .deadLine(project.getDeadline())
                        .technology(project.getTechnology())
                        .build())
                .toList();
    }

    @Transactional
    public List<ProjectsDto> findAllProjectsByStatus(Status status) {

        return projectsRepository.findAllByProjectStatus(status).stream()
                .map(project -> ProjectsDto.builder()
                        .name(project.getName())
                        .description(project.getDescription())
                        .period(project.getPeriod())
                        .projectStatus(project.getProjectStatus())
                        .startDate(project.getStartDate())
                        .deadLine(project.getDeadline())
                        .technology(project.getTechnology())
                        .build())
                .toList();
    }

    @Transactional
    public List<ProjectsDto> findAllProjectsByPeriod(Period period) {

        return projectsRepository.findAllByPeriod(period).stream()
                .map(project -> ProjectsDto.builder()
                .name(project.getName())
                .description(project.getDescription())
                .period(project.getPeriod())
                .projectStatus(project.getProjectStatus())
                .startDate(project.getStartDate())
                .deadLine(project.getDeadline())
                .technology(project.getTechnology())
                .build()).toList();
    }

    public List<Status> getAllStatus() {
        return List.of(Status.values());
    }

    public List<Period> getAllPeriods() {
        return List.of(Period.values());
    }


    @Transactional
    public Projects deleteProject(String name) {
        Projects projects = projectsRepository.findByName(name).orElseThrow();

        Status projectStatus = projects.getProjectStatus();

        if (projectStatus == Status.InProgress || projectStatus == Status.Closing || projectStatus == Status.Closed) {
            throw new RuntimeException("Project with status " + projectStatus + " cannot be deleted");
        }

        projectsRepository.delete(projects);
        return projects;
    }
}

