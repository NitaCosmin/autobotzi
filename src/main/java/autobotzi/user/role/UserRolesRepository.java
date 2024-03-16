package autobotzi.user.role;

import autobotzi.config.OpenAPIConfig;
import autobotzi.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {
    Collection<UserRoles> findAllByRoleName(String roleName);

}
