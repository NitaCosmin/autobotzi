package autobotzi.image;
import autobotzi.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ImageRepository extends JpaRepository<UserImage, UUID> {

    UserImage findByUser(Users user);

    Optional<UserImage> findByName(String fileName);
}
