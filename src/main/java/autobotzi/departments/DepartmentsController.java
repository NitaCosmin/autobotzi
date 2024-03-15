package autobotzi.departments;

import autobotzi.departments.dto.DepartmentAdminView;
import autobotzi.departments.dto.DepartmentsDto;
import autobotzi.departments.dto.DepartmentsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentsController {

    private final DepartmentsService departmentsService;

    @GetMapping
    public List<DepartmentsResponse> getAllDepartments() {
        return departmentsService.getAllDepartments();
    }
    @GetMapping("/all")
    public List<DepartmentAdminView> getDepartments() {
        return departmentsService.getDepartments();
    }

    @PostMapping
    public Departments addDepartment(@RequestBody DepartmentsDto departmentsDto, @RequestParam String adminEmail) {
        return departmentsService.addDepartment(departmentsDto, adminEmail);
    }

    @PutMapping("/update-manager")
    public Departments updateDepartmentManager(@RequestParam String email, @RequestParam String departmentName) {
        return departmentsService.updateDepartmentManager(email, departmentName);
    }

    @PutMapping("/update")
    public void updateDepartmentByDepartmentName(@RequestParam String name,@RequestBody DepartmentsDto departmentsDto) {
        departmentsService.updateDepartmentByDepartmentName(name,departmentsDto);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentsService.deleteDepartment(id);
    }
}
