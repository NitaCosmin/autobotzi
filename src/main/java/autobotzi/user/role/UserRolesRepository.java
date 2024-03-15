package autobotzi.user.role;

import autobotzi.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {
    Collection<UserRoles> findAllByRoleName(String roleName);

    List<UserRoles> findByUser(Users user);
}
