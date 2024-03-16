package autobotzi.user.notifications.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class NotificationsDto {

    private String message;

    private Boolean unread;

    @JsonFormat(pattern = "yyyy-MM-dd ")
    private Date created_at;

    private String user;
    private String userReciver;
}
