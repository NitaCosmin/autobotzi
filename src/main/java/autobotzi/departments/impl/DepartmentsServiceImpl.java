package autobotzi.departments.impl;

import autobotzi.departments.*;
import autobotzi.departments.dto.DepartmentAdminView;
import autobotzi.departments.dto.DepartmentsDto;
import autobotzi.departments.dto.DepartmentsResponse;
import autobotzi.user.UserRepository;
import autobotzi.user.Users;
import autobotzi.user.utils.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
public class DepartmentsServiceImpl implements DepartmentsService {

    private final DepartmentsRepository departmentsRepository;
    private final UserRepository userRepository;
    private final DepartmentsMembersRepository departmentsMembersRepository;


    public List<DepartmentsResponse> getAllDepartments() {
        return departmentsRepository.findAll().stream()
                .map(department -> {
                    DepartmentsResponse departmentDto = new DepartmentsResponse();
                    departmentDto.setName(department.getName());
                    departmentDto.setDescription(department.getDescription());
                    Users user = department.getUser();
                    if (user != null) {
                        departmentDto.setDepartmentManager(user.getName());
                    }
                    return departmentDto;
                })
                .collect(Collectors.toList());
    }
    public List<DepartmentAdminView> getDepartments() {
        return departmentsRepository.findAll().stream()
                .map(department -> {
                    DepartmentAdminView departmentDto = new DepartmentAdminView();
                    departmentDto.setName(department.getName());
                    Users user = department.getUser();
                    if (user != null) {
                        departmentDto.setDepartmentManager(user.getName());
                    }
                    return departmentDto;
                })
                .collect(Collectors.toList());
    }

    public Departments addDepartment(DepartmentsDto departmentsDto, String email) {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Departments existingDepartment = departmentsRepository.findByUser(user).orElse(null);

        if (existingDepartment != null) {
            existingDepartment.setName(departmentsDto.getName());
            existingDepartment.setDescription(departmentsDto.getDescription());
            return departmentsRepository.save(existingDepartment);
        } else {
            if (!user.getRole().equals(Role.ADMIN)) {
                throw new IllegalArgumentException("User is not an admin");
            }
            return departmentsRepository.save(Departments.builder()
                    .name(departmentsDto.getName())
                    .description(departmentsDto.getDescription())
                    .user(user)
                    .organization(user.getOrganization())
                    .build());
        }
    }

    public Departments updateDepartmentManager(String email, String departmentName) {

        return departmentsRepository.save(
                userRepository.findByEmail(email)
                        .map(user -> departmentsRepository.findByName(departmentName)
                                .map(department -> {
                                    department.setUser(user);
                                    return department;
                                })
                                .orElseThrow(() -> new IllegalArgumentException("Department not found")))
                        .orElseThrow(() -> new IllegalArgumentException("User not found")));
    }

    public Departments updateDepartmentByDepartmentName(String name,DepartmentsDto departmentsDto) {
        return departmentsRepository.save(
                departmentsRepository.findByName(name)
                        .map(department -> {
                            department.setName(departmentsDto.getName());
                            department.setDescription(departmentsDto.getDescription());
                            return department;
                        })
                        .orElseThrow(() -> new IllegalArgumentException("Department not found")));

    }
    public List<DepartmentsResponse> getAllDepartmentsOrganizations(String email) {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return departmentsRepository.findAll().stream()
                .filter(department -> department.getOrganization() != null &&
                        department.getOrganization().equals(user.getOrganization()))
                .map(department -> {
                    DepartmentsResponse departmentDto = new DepartmentsResponse();
                    departmentDto.setName(department.getName());
                    departmentDto.setDescription(department.getDescription());
                    Users departmentUser = department.getUser();
                    if (departmentUser != null) {
                        departmentDto.setDepartmentManager(departmentUser.getName());
                    }
                    return departmentDto;
                })
                .collect(Collectors.toList());
    }



}
