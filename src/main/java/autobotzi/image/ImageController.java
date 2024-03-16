package autobotzi.image;

import autobotzi.image.dto.ImageIdNameDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadImage(@RequestBody MultipartFile file) {
//        try {
//            imageService.uploadImage(file);
//            return ResponseEntity.status(HttpStatus.CREATED).body("Image uploaded successfully.");
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed.");
//        }
//    }
//
//    @GetMapping("/getImage-name/{imageId}")
//    public ResponseEntity<byte[]> getImageByName(@PathVariable UUID imageId) {
//        byte[] image = imageService.getImage(imageId);
//
//        return ResponseEntity.status(HttpStatus.OK)
//                .contentType(MediaType.IMAGE_PNG)
//                .body(image);
//    }
//    @DeleteMapping("/delete/{imageId}")
//    public ResponseEntity<String> deleteImage(@PathVariable UUID imageId) {
//        imageService.deleteImage(imageId);
//        return ResponseEntity.ok("Image deleted successfully.");
//    }
//    @GetMapping("/allImageIdsAndNames")
//    public List<ImageIdNameDto> getAllImageIdsAndNames() {
//        return imageService.getAllImageIdsAndNames();
//    }

}
