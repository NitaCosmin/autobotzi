package autobotzi.user.impl;

import autobotzi.departments.Departments;
import autobotzi.departments.DepartmentsMembersRepository;
import autobotzi.departments.DepartmentsRepository;
import autobotzi.project.Projects;
import autobotzi.project.ProjectsRepository;
import autobotzi.project.assignments.ProjectAssignmentsRepository;
import autobotzi.skills.SkillsRepository;
import autobotzi.user.UserRepository;
import autobotzi.user.UserService;
import autobotzi.user.Users;
import autobotzi.user.dto.UsersAdminViewDto;
import autobotzi.user.dto.UsersDto;
import autobotzi.user.dto.UsersOrganizationsDto;
import autobotzi.user.dto.UsersPreViewDto;
import autobotzi.user.notifications.NotificationsRepository;
import autobotzi.user.skill.UserSkillsRepository;
import autobotzi.user.utils.Role;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final DepartmentsRepository departmentsRepository;
    private final DepartmentsMembersRepository departmentsMembersRepository;
    private final NotificationsRepository notificationsRepository;
    private final UserSkillsRepository userSkillsRepository;
    private final ProjectsRepository projectsRepository;
    private final ProjectAssignmentsRepository projectAssignmentsRepository;
    private final SkillsRepository skillsRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                return userRepository.findByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }


    public List<UsersDto> getAll() {
        return userRepository.findAll().stream()
                .map(user -> UsersDto.builder()
                        .name(user.getName())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .build())
                .collect(java.util.stream.Collectors.toList());
    }

    public List<UsersPreViewDto> getAllPreView() {
        return userRepository.findAll().stream()
                .map(user -> UsersPreViewDto.builder()
                        .name(user.getName())
                        .email(user.getEmail())
                        .build())
                .collect(java.util.stream.Collectors.toList());
    }

    public List<UsersAdminViewDto> getAllAdminView() {
        return userRepository.findAll().stream()
                .map(user -> UsersAdminViewDto.builder()
                        .name(user.getName())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .created_at(user.getCreated_at())
                        .build())
                .collect(java.util.stream.Collectors.toList());
    }

    public List<UsersOrganizationsDto> getAllUsersWithOrganizations() {
        return userRepository.findAll().stream()
                .map(user -> new UsersOrganizationsDto(
                        user.getName(),
                        user.getOrganization().getName()))
                .collect(Collectors.toList());
    }

    public List<UsersDto> getUsersByRole(Role role) {
        return userRepository.findByRole(role).stream()
                .map(user -> UsersDto.builder()
                        .name(user.getName())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .build())
                .collect(Collectors.toList());
    }

    public List<Role> getAllRoles() {
        return List.of(Role.values());

    }

    public UsersDto getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(user -> UsersDto.builder()
                        .name(user.getName())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .build())
                .orElseThrow(() -> new IllegalArgumentException("User skills not found"));
    }

    public List<UsersDto> getUsersByDepartment(String departmentName) {
        return departmentsMembersRepository.findByDepartment(
                        departmentsRepository.findByName(departmentName)
                                .orElseThrow(() ->
                                        new IllegalArgumentException("Department not found")))
                .stream()
                .map(departmentsMembers -> UsersDto.builder()
                        .name(departmentsMembers.getUser().getName())
                        .email(departmentsMembers.getUser().getEmail())
                        .role(departmentsMembers.getUser().getRole())
                        .build())
                .collect(Collectors.toList());
    }

    public UsersDto updateUserRole(String email, Role role) {
        return userRepository.findByEmail(email)
                .map(user -> {
                    user.setRole(role);
                    return userRepository.save(user);
                })
                .map(user -> UsersDto.builder()
                        .name(user.getName())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .build())
                .orElse(null);
    }

    public UsersDto updateUserByEmail(String email, String name) {
        return userRepository.findByEmail(email)
                .map(user -> {
                    user.setName(name);
                    return userRepository.save(user);
                })
                .map(user -> UsersDto.builder()
                        .name(user.getName())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .build())
                .orElse(null);
    }

    public Users deleteUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(user -> {
                    userRepository.delete(user);
                    return user;
                })
                .orElse(null);
    }

    public void deallocatePM(Users users) {
        Projects project = projectsRepository.findByUser(users).orElse(null);
        if (project == null) {
            return;
        }
        project.setUser(null);
        projectsRepository.save(project);
    }

    public void deallocateDM(Users users) {
        Departments departments = departmentsRepository.findByUser(users).orElse(null);
        if (departments == null) {
            return;
        }
        departments.setUser(null);
        departmentsRepository.save(departments);
    }
    @Transactional
    public void deleteUserFromEverywhere(String email) {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        deallocatePM(user);
        notificationsRepository.deleteByUser(user);
        userSkillsRepository.deleteByUser(user);
        deallocateDM(user);
        departmentsMembersRepository.deleteByUser(user);
        projectAssignmentsRepository.deleteByUser(user);
        skillsRepository.deleteByUser(user);

        userRepository.delete(user);
    }

}




