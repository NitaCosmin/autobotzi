package autobotzi.email;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"
        ,"http://atc-2024-autobotzi-fe-linux-web-app.azurewebsites.net/"
        ,"https://frontend-jf48yfydc-eduard-ionel-eduards-projects.vercel.app/"
        ,"https://front-autobotzi-c55123365842.herokuapp.com/"})
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send")
    public String sendMail(@RequestBody Email email) {
        {
            String status
                    = emailService.sendSimpleMail(email);

            return status;
        }
    }
    @PostMapping("/send-admin")
    public String sendMailAdmin(@RequestBody Email email) {
        {
            String status
                    = emailService.sendSimpleMailAdmin(email);

            return status;
        }
    }
}
