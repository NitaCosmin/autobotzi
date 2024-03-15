package autobotzi.user.impl;

import autobotzi.user.notifications.NotificationsRepository;
import autobotzi.user.notifications.NotificationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationsServiceImpl implements NotificationsService {

    private final NotificationsRepository notificationsRepository;
}
