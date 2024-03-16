package autobotzi.image;

import autobotzi.user.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Entity

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_image")
public class UserImage implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String type;
    @Lob
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;


}

