package autobotzi.departments;

import autobotzi.departments.dto.DepartmentAdminView;
import autobotzi.departments.dto.DepartmentsDto;
import autobotzi.departments.dto.DepartmentsMembersDto;
import autobotzi.departments.dto.DepartmentsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"
        ,"http://atc-2024-autobotzi-fe-linux-web-app.azurewebsites.net/"
        ,"https://frontend-jf48yfydc-eduard-ionel-eduards-projects.vercel.app/"
        ,"https://front-autobotzi-c55123365842.herokuapp.com/"})
public class DepartmentsController {

    private final DepartmentsService departmentsService;
    private final DepartmentsMembersService departmentsMembersService;

    @GetMapping("/all")
    public List<DepartmentsResponse> getAllDepartments() {
        return departmentsService.getAllDepartments();
    }
    @GetMapping("/all-organizations")
    public List<DepartmentsResponse> getAllDepartmentsOrganizetions(@AuthenticationPrincipal UserDetails userDetails) {
        return departmentsService.getAllDepartmentsOrganizations(userDetails.getUsername());
    }
    @GetMapping("/all-admin-view")
    public List<DepartmentAdminView> getDepartments() {
        return departmentsService.getDepartments();
    }

    @PostMapping("/add")
    public Departments addDepartment(@RequestBody DepartmentsDto departmentsDto, @AuthenticationPrincipal UserDetails userDetails) {
        return departmentsService.addDepartment(departmentsDto, userDetails.getUsername());
    }
    //===============================================================================================================
    //Departments Members
    //===============================================================================================================

    @PutMapping("/update-manager")
    public Departments updateDepartmentManager(@RequestParam String email, @RequestParam String departmentName) {
        return departmentsService.updateDepartmentManager(email, departmentName);
    }

    @PutMapping("/update")
    public Departments updateDepartmentByDepartmentName(@RequestParam String name,@RequestBody DepartmentsDto departmentsDto) {
       return departmentsService.updateDepartmentByDepartmentName(name,departmentsDto);
    }

    @PostMapping("/members/assign")
    public DepartmentsMembers assignDepartmentToUser(@RequestBody DepartmentsMembersDto departmentsMembersDto) {
        return departmentsMembersService.assignDepartmentToUser(departmentsMembersDto);
    }
    @GetMapping("/members")
    public List<DepartmentsMembersDto> getDepartmentsMembers() {
        return departmentsMembersService.getDepartmentsMembers();
    }
    @GetMapping("members/by-department")
    public List<DepartmentsMembersDto> getDepartmentsMembersByDepartment(@RequestParam String departmentName) {
        return departmentsMembersService.getDepartmentsMembersByDepartment(departmentName);
    }
    @GetMapping("members/count")
    public Integer getDepartmentMembersCount(@RequestParam String departmentName) {
        return departmentsMembersService.getDepartmentMembersCount(departmentName);
    }
    @DeleteMapping("members/delete")
    public void deleteDepartmentMember(@RequestParam String email) {
        departmentsMembersService.deleteDepartmentMember(email);
    }
}
