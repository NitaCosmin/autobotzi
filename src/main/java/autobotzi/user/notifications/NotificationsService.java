package autobotzi.user.notifications;

import autobotzi.user.notifications.dto.NotificationsSend;
import autobotzi.user.notifications.dto.NotificationsView;

import java.util.List;
import java.util.Optional;

public interface NotificationsService {
    List<NotificationsView> getAllNotificationsOfUser(String user);
    Notifications sendNotification(NotificationsSend notificationsSend, String user);
    Optional<NotificationsView> markAsRead(String user);
}
