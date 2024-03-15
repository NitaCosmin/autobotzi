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

    public Departments addDepartment(DepartmentsDto departmentsDto, String adminEmail) {
        Users admin = userRepository.findByEmail(adminEmail)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found"));

        Departments department = Departments.builder()
                .name(departmentsDto.getName())
                .description(departmentsDto.getDescription())
                .organization(admin.getOrganization())
                .build();

        return departmentsRepository.save(department);
    }
    public Departments updateDepartmentManager(String email, String departmentName) {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setRole(Role.DEPARTMENT_MANAGER);
        userRepository.save(user);

        Departments department = departmentsRepository.findByName(departmentName)
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));

        DepartmentsMembers departmentsMembers = new DepartmentsMembers();
        departmentsMembers.setUser(user);
        departmentsMembers.setDepartment(department);
        departmentsMembersRepository.save(departmentsMembers);

        department.setUser(user);

        return departmentsRepository.save(department);
    }

    public void updateDepartmentByDepartmentName(String name,DepartmentsDto departmentsDto) {
        Departments department = departmentsRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));
        department.setName(departmentsDto.getName());
        department.setDescription(departmentsDto.getDescription());
        departmentsRepository.save(department);
    }
    public void deleteDepartment(Long id) {
        departmentsRepository.deleteById(id);
    }


}
