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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        return projectsRepository.findByName(projectsDto.getName())
                .filter(p -> {
                    throw new RuntimeException("A project with the name " + projectsDto.getName() + " already exists");
                })
                .orElseGet(() -> projectsRepository.save(Projects.builder()
                        .name(projectsDto.getName())
                        .description(projectsDto.getDescription())
                        .period(projectsDto.getPeriod())
                        .projectStatus(projectsDto.getProjectStatus())
                        .startDate(projectsDto.getStartDate())
                        .deadline(projectsDto.getDeadLine())
                        .technology(projectsDto.getTechnology())
                        .organization(userRepository.findByEmail(email)
                                .map(Users::getOrganization)
                                .orElseThrow(() -> new RuntimeException("User not found")))
                        .build()));
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        return projectsRepository.save(
                userRepository.findByEmail(email)
                        .filter(user -> admin.equals(currentPrincipalName))
                        .map(user -> {
                            user.setRole(Role.valueOf(roleRepository.findByName("PROJECT_MANAGER")
                                    .orElseThrow(() -> new RuntimeException("Role not found")).getName()));
                            return user;
                        })
                        .map(user -> {
                            Projects project = projectsRepository.findByName(name).orElseThrow();
                            project.setUser(user);
                            return project;
                        })
                        .orElseThrow(() -> new RuntimeException("Project Manager not found or only the authenticated admin can modify the project"))
        );
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public Projects updateProjectDateByProjectName(String email, String name, ProjectsDateDto projectsDto) {
        return projectsRepository.save(
                userRepository.findByEmail(email)
                        .map(user -> projectsRepository.findByName(name)
                                .filter(projects -> projects.getOrganization().equals(user.getOrganization()))
                                .map(projects -> {
                                    projects.setStartDate(projectsDto.getStartDate());
                                    projects.setDeadline(projectsDto.getDeadLine());
                                    return projects;
                                })
                                .orElseThrow(() -> new RuntimeException("Project not found or user does not belong to the same organization as the project"))
                        )
                        .orElseThrow(() -> new RuntimeException("User not found")));
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
        return projectsRepository.findByName(name)
                .filter(projects -> {
                    Status projectStatus = projects.getProjectStatus();
                    if (projectStatus == Status.InProgress || projectStatus == Status.Closing || projectStatus == Status.Closed) {
                        throw new RuntimeException("Project with status " + projectStatus + " cannot be deleted");
                    }
                    return true;
                })
                .map(projects -> {
                    projectsRepository.delete(projects);
                    return projects;
                })
                .orElseThrow();
    }
}

