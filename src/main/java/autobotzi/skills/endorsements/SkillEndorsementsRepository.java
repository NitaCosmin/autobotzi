package autobotzi.skills.endorsements;

import autobotzi.departments.DepartmentsMembers;
import autobotzi.user.skill.UserSkills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface SkillEndorsementsRepository extends JpaRepository<SkillEndorsements, Long> {
    Optional<SkillEndorsements> findByUserSkill(UserSkills userSkill);



}
