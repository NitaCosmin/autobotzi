package autobotzi.project.assignments;

import autobotzi.user.Users;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface ProjectAssignmentsRepository extends JpaRepository<ProjectAssignments, Long> {
    Optional<ProjectAssignments> findByUser(Users user);
}
