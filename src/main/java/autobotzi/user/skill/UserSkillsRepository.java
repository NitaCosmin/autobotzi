package autobotzi.user.skill;

import autobotzi.skills.Skills;
import autobotzi.user.Users;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserSkillsRepository extends JpaRepository<UserSkills, Long> {

    Optional<UserSkills> findByUser(Users user);


    List<UserSkills> findByValidatedTrue();

    Collection<UserSkills> findByValidatedFalse();
}
