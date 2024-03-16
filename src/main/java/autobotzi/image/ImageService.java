package autobotzi.image;

import autobotzi.image.dto.ImageIdNameDto;
import autobotzi.image.utill.ImageUtil;
import autobotzi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {


    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
//    public void uploadImage(MultipartFile file) throws IOException {
//        String fileName = file.getOriginalFilename();
//        assert fileName != null;
//        fileName = fileName.substring(0, fileName.length() - 4);
//        fileName = LocalDate.now() + "_" +
//                LocalTime.now().toString().substring(0, 8) + "_" + fileName + ".PNG";
//        imageRepository.save(UserImage.builder()
//                .name(fileName)
//                .type(file.getContentType())
//                .user(userRepository.findByUsername("admin").get())
//                .data(ImageUtil.compressImage(file.getBytes())).build());
//    }

    @Transactional
    @Cacheable(value = "images", key = "#imageId")
    public byte[] getImage(UUID imageId) {
        Optional<UserImage> dbImage = imageRepository.findById(imageId);


        return ImageUtil.decompressImage(dbImage.map(UserImage::getData).orElse(null));
    }
    public void deleteImage(UUID imageId) {
        imageRepository.deleteById(imageId);
    }
    public List<ImageIdNameDto> getAllImageIdsAndNames() {
        List<UserImage> userImages = imageRepository.findAll();

        // Map the Image objects to ImageIdNameDto using the builder
        List<ImageIdNameDto> imageIdNameList = userImages.stream()
                .map(userImage -> ImageIdNameDto.builder()
                        .id(String.valueOf(userImage.getId()))
                        .name(userImage.getName())
                        .build())
                .collect(Collectors.toList());

        return imageIdNameList;
    }
}

