package autobotzi.user.notifications;

import autobotzi.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationsRepository extends JpaRepository<Notifications, Long> {
    Optional<Notifications> findByUser(Users user);
}
