package autobotzi.user.notifications;

import autobotzi.user.notifications.dto.NotificationsDto;
import autobotzi.user.notifications.dto.NotificationsSend;
import autobotzi.user.notifications.dto.NotificationsView;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationsController {

    private final NotificationsService notificationsService;

    @GetMapping
    public List<NotificationsView> getNotifications(@AuthenticationPrincipal UserDetails userDetails) {
        return notificationsService.getAllNotificationsOfUser( userDetails.getUsername());
    }
    @PostMapping
    public Notifications sendNotification(@RequestBody NotificationsSend notificationsSend,
                                         @AuthenticationPrincipal UserDetails userDetails) {
        return notificationsService.sendNotification(notificationsSend, userDetails.getUsername());
    }
    @PutMapping
    public Optional<NotificationsView> markAsRead(@AuthenticationPrincipal UserDetails userDetails) {
        return notificationsService.markAsRead(userDetails.getUsername());
    }
}
