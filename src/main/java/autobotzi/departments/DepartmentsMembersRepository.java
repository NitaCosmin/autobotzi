package autobotzi.departments;

import autobotzi.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentsMembersRepository extends JpaRepository<DepartmentsMembers, Long> {


    Collection<DepartmentsMembers> findByDepartment(Departments department);

    Collection<DepartmentsMembers> findAllByDepartmentName(String departmentName);

    Optional<List<DepartmentsMembers>> findByUser(Users user);
}
