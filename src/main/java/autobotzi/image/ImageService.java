package autobotzi.image;

import autobotzi.image.utill.ImageUtil;
import autobotzi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageService {


    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    @Transactional
    @CacheEvict(value = "images", key = "#email")
    public void uploadImage(MultipartFile file,String email) throws IOException {
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        fileName = email+ ".PNG";
        if(imageRepository.findByName(fileName).isPresent()){
            imageRepository.delete(imageRepository.findByName(fileName).orElseThrow(()
                    ->new RuntimeException("Image not found")));
        }
        imageRepository.save(UserImage.builder()
                .name(fileName)
                .type(file.getContentType())
                .user(userRepository.findByEmail(email).orElseThrow(()
                                ->new RuntimeException("User not found")))
                .data(ImageUtil.compressImage(file.getBytes())).build());
    }

    @Transactional
    @Cacheable(value = "images", key = "#email")
    public byte[] getImage(String email) {
        UserImage dbImage = imageRepository.findByUser(userRepository.findByEmail(email).orElseThrow(()
                ->new RuntimeException("User not found")));
        return ImageUtil.decompressImage(dbImage.getData());
    }

}

