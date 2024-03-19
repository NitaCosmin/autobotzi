package autobotzi.image;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"
        ,"http://atc-2024-autobotzi-fe-linux-web-app.azurewebsites.net/"
        ,"https://frontend-jf48yfydc-eduard-ionel-eduards-projects.vercel.app/"
        ,"https://front-autobotzi-c55123365842.herokuapp.com/"})
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public String uploadImage(@RequestBody MultipartFile file, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            imageService.uploadImage(file, userDetails.getUsername());
            return "Image uploaded successfully.";
        } catch (IOException e) {
            return "Image upload failed.";
        }
    }

    @GetMapping("/getImage-name/")
    public ResponseEntity<byte[]> getImageByName(@AuthenticationPrincipal UserDetails userDetails) {
//        return imageService.getImage(userDetails.getUsername());
        byte[] image = imageService.getImage(userDetails.getUsername());

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_PNG)
                .body(image);
    }



}
