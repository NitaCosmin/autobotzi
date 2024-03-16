package autobotzi.image.dto;

import autobotzi.image.UserImage;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(builderClassName = "ImageUploadDtoBuilder")
@AllArgsConstructor
@NoArgsConstructor
public class UserImageUploadDto extends UserImage {
    private String name;
    private String type;
    @Lob
    private byte[] data;
    public static ImageUploadDtoBuilder builder() {
        return new ImageUploadDtoBuilder();
    }

    public static class ImageUploadDtoBuilder extends ImageBuilder {
    }
}
