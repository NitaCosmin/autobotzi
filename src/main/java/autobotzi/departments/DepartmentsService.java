package autobotzi.departments;

import autobotzi.departments.dto.DepartmentAdminView;
import autobotzi.departments.dto.DepartmentsDto;
import autobotzi.departments.dto.DepartmentsResponse;
import autobotzi.user.Users;

import java.util.List;

public interface DepartmentsService {


    Departments addDepartment(DepartmentsDto departmentsDto, String adminEmail);

    void deleteDepartment(Long id);
    List<DepartmentsResponse> getAllDepartments();
    List<DepartmentAdminView> getDepartments();
    Departments updateDepartmentManager(String email,String departmentName);
    void updateDepartmentByDepartmentName(String name,DepartmentsDto departmentsDto);

}