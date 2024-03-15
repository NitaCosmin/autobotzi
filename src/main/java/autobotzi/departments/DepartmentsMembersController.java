package autobotzi.departments;

import autobotzi.departments.dto.DepartmentsMembersDto;
import autobotzi.user.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments-members")
@RequiredArgsConstructor
public class DepartmentsMembersController {

    private final DepartmentsMembersService departmentsMembersService;



    @PostMapping
    public DepartmentsMembers assignDepartmentToUser(@RequestBody DepartmentsMembersDto departmentsMembersDto) {
        return departmentsMembersService.assignDepartmentToUser(departmentsMembersDto);
    }
    @GetMapping
    public List<DepartmentsMembersDto> getDepartmentsMembers() {
        return departmentsMembersService.getDepartmentsMembers();
    }
    @GetMapping("/by-department")
    public List<DepartmentsMembersDto> getDepartmentsMembersByDepartment(@RequestParam String departmentName) {
        return departmentsMembersService.getDepartmentsMembersByDepartment(departmentName);
    }
    @GetMapping("/count")
    public Integer getDepartmentMembersCount(@RequestParam String departmentName) {
        return departmentsMembersService.getDepartmentMembersCount(departmentName);
    }
    @DeleteMapping
    public void deleteDepartmentMember(@RequestParam String email) {
        departmentsMembersService.deleteDepartmentMember(email);
    }


}
