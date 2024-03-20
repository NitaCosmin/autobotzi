package autobotzi.user.notifications.impl;

import autobotzi.user.UserRepository;
import autobotzi.user.notifications.Notifications;
import autobotzi.user.notifications.NotificationsRepository;
import autobotzi.user.notifications.NotificationsService;
import autobotzi.user.notifications.dto.NotificationsSend;
import autobotzi.user.notifications.dto.NotificationsView;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationsServiceImpl implements NotificationsService {

    private final NotificationsRepository notificationsRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<NotificationsView> getAllNotificationsOfUser(String user) {
        return notificationsRepository.findAll().stream()
                .filter(notifications -> notifications.getUserReciver().getEmail().equals(user))
                .map(notifications -> NotificationsView.builder()
                        .message(notifications.getMessage())
                        .created_at(notifications.getCreated_at())
                        .user(notifications.getUser().getEmail())
                        .build())
                .collect(Collectors.toList());

    }
    @Transactional
    public Notifications sendNotification(NotificationsSend notificationsSend, String user) {
        return notificationsRepository.save(Notifications.builder()
                .message(notificationsSend.getMessage())
                .created_at(Date.from(Instant.now()))
                        .unread(true)
                .user(userRepository.findByEmail(user).orElseThrow(() ->
                        new RuntimeException("User not found")))
                .userReciver(userRepository.findByEmail(notificationsSend.getUserReciver()).orElseThrow(() ->
                                new RuntimeException("Receiver user not found")))
                .build());
    }
    @Transactional
    public Optional<NotificationsView> markAsRead(String user) {
        return notificationsRepository.findAll().stream()
                .filter(notifications -> notifications.getUserReciver().getEmail().equals(user) && notifications.getUnread())
                .sorted(Comparator.comparing(Notifications::getCreated_at).reversed())
                .findFirst()
                .map(notifications -> {
                    notifications.setUnread(false);
                    notificationsRepository.save(notifications);
                    return NotificationsView.builder()
                            .message(notifications.getMessage())
                            .created_at(notifications.getCreated_at())
                            .user(notifications.getUser().getEmail())
                            .build();
                });
    }
}
