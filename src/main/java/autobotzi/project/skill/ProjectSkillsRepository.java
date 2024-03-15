package autobotzi.project.skill;

import autobotzi.project.Projects;
import autobotzi.skills.Skills;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ProjectSkillsRepository extends JpaRepository<ProjectSkills, Long> {

    Optional<ProjectSkills> findBySkill(Skills skill);

    Optional<ProjectSkills> findByProject(Projects project);
}
