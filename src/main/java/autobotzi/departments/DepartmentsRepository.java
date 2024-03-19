package autobotzi.departments;

import autobotzi.departments.dto.DepartmentsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentsRepository extends JpaRepository<Departments, Long> {
    Optional<Departments> findByName(String name);
}
