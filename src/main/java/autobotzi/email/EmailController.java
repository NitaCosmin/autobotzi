package autobotzi.email;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
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
