package autobotzi.departments;

import autobotzi.departments.dto.DepartmentAdminView;
import autobotzi.departments.dto.DepartmentsDto;
import autobotzi.departments.dto.DepartmentsResponse;
import autobotzi.user.Users;

import java.util.List;

public interface DepartmentsService {


    Departments addDepartment(DepartmentsDto departmentsDto, String adminEmail);


    List<DepartmentsResponse> getAllDepartments();
    List<DepartmentAdminView> getDepartments();
    Departments updateDepartmentManager(String email,String departmentName);
    Departments updateDepartmentByDepartmentName(String name,DepartmentsDto departmentsDto);
    List<DepartmentsResponse> getAllDepartmentsOrganizations(String email);

}
