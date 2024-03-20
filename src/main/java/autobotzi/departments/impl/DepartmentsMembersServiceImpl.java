package autobotzi.departments.impl;

import autobotzi.departments.*;
import autobotzi.departments.dto.DepartmentsMembersDto;
import autobotzi.user.UserRepository;
import autobotzi.user.Users;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentsMembersServiceImpl implements DepartmentsMembersService {


    private final DepartmentsMembersRepository departmentsMembersRepository;
    private final DepartmentsRepository departmentsRepository;
    private final UserRepository userRepository;
    public DepartmentsMembers assignDepartmentToUser(DepartmentsMembersDto departmentsMembersDto) {
    return departmentsMembersRepository.save(
            userRepository.findByEmail(departmentsMembersDto.getEmail())
                    .map(user -> DepartmentsMembers.builder()
                            .user(user)
                            .department(departmentsRepository.findByName(departmentsMembersDto.getDepartment())
                                    .orElseThrow(() -> new IllegalArgumentException("Department not found")))
                            .build())
                    .orElseThrow(() -> new IllegalArgumentException("User not found")
                    ));
    }
    public DepartmentsMembers updateDepartmentToUser(DepartmentsMembersDto departmentsMembersDto) {
        return departmentsMembersRepository.save(
                userRepository.findByEmail(departmentsMembersDto.getEmail())
                        .map(user -> departmentsMembersRepository.findByUser(user)
                                .map(departmentsMembers -> DepartmentsMembers.builder()
                                        .user(user)
                                        .department(departmentsRepository.findByName(departmentsMembersDto.getDepartment())
                                                .orElseThrow(() -> new IllegalArgumentException("Department not found")))
                                        .build())
                                .orElseThrow(() -> new IllegalArgumentException("Department member not found")))
                        .orElseThrow(() -> new IllegalArgumentException("User not found")
                        ));
    }
    public List<DepartmentsMembersDto> getDepartmentsMembers() {
        return departmentsMembersRepository.findAll().stream()
                .map(departmentsMembers -> DepartmentsMembersDto.builder()
                        .email(departmentsMembers.getUser().getEmail())
                        .department(departmentsMembers.getDepartment().getName())
                        .build())
                .collect(Collectors.toList());
    }
    public List<DepartmentsMembersDto> getDepartmentsMembersByDepartment(String departmentName) {
        return departmentsMembersRepository.findAllByDepartmentName(departmentName).stream()
                .map(departmentsMembers -> DepartmentsMembersDto.builder()
                        .email(departmentsMembers.getUser().getEmail())
                        .department(departmentsMembers.getDepartment().getName())
                        .build())
                .collect(Collectors.toList());
    }
    public Integer getDepartmentMembersCount(String departmentName) {
        return departmentsMembersRepository.findAllByDepartmentName(departmentName).size();
    }

    public void deleteDepartmentMember(String email) {
        DepartmentsMembers departmentsMembers = departmentsMembersRepository.findByUserEmail(email)
                .orElse(null);
        if (departmentsMembers == null) {
            return;
        }
        departmentsMembers.setUser(null);
        departmentsMembersRepository.save(departmentsMembers);
    }
}
