package autobotzi.user.notifications;

import autobotzi.user.Users;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notifications")
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String message;

    private Boolean unread;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "EET")
    private Date created_at;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
}
