package autobotzi.departments;

import autobotzi.departments.dto.DepartmentsMembersDto;
import autobotzi.user.Users;

import java.util.List;

public interface DepartmentsMembersService {


    DepartmentsMembers assignDepartmentToUser(DepartmentsMembersDto departmentsMembersDto);
    List<DepartmentsMembersDto> getDepartmentsMembers();
    List<DepartmentsMembersDto> getDepartmentsMembersByDepartment(String departmentName);
    Integer getDepartmentMembersCount(String departmentName);
    void deleteDepartmentMember(String email);
}
