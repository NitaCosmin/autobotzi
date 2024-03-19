package autobotzi.skills;

import autobotzi.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillsRepository extends JpaRepository<Skills, Long> {
    Optional<Skills> findByName(String name);


    Optional<Skills> findByUser(Users user);
}
