package autobotzi.image.dto;

import autobotzi.image.UserImage;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserImageUploadDto extends UserImage {
    private String name;
    private String type;
    @Lob
    private byte[] data;

}
