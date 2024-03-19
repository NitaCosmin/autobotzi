package autobotzi.departments;

import autobotzi.departments.dto.DepartmentsResponse;
import autobotzi.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentsRepository extends JpaRepository<Departments, Long> {
    Optional<Departments> findByName(String name);
    Optional<Departments> findByUser(Users users);
}
