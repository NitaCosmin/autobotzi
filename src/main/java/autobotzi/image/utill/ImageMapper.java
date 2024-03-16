package autobotzi.image.utill;

import autobotzi.image.UserImage;
import autobotzi.image.dto.UserImageUploadDto;

public class ImageMapper {

    public static UserImageUploadDto mapImageToDto(UserImage userImage) {
        return UserImageUploadDto.builder()
                .name(userImage.getName())
                .type(userImage.getType())
                .data(userImage.getData())
                .build();
    }

    public static UserImage mapDtoToImage(UserImageUploadDto imageDto) {
        return UserImage.builder()
                .name(imageDto.getName())
                .type(imageDto.getType())
                .data(imageDto.getData())
                .build();
    }
}
