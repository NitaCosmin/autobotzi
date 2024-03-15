package autobotzi.project;

import autobotzi.project.utils.Period;
import autobotzi.project.utils.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectsRepository extends JpaRepository<Projects, Long> {


    Optional<Projects> findByName(String name);

    List<Projects> findAllByProjectStatus(Status status);

    List<Projects> findAllByPeriod(Period period);
}
