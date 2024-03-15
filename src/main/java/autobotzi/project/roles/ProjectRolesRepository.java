package autobotzi.project.roles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRolesRepository  extends JpaRepository<ProjectRoles, Long> {
}
