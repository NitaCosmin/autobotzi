package autobotzi.email;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender javaMailSender;
    private final String sender = "cosmin.nita1@student.usv.ro";

    public String sendSimpleMail(Email email) {

        try {

            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();
            String signUpLink = "http://atc-2024-autobotzi-fe-linux-web-app.azurewebsites.net \n\n ";

            mailMessage.setFrom(sender);
            mailMessage.setTo(email.getRecipient());
            mailMessage.setText(email.getMessage());
            mailMessage.setSubject(email.getSubject());
            mailMessage.setText(email.getMessage() + "\n\n" + signUpLink);

            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        } catch (Exception e) {
            return "Error while Sending Mail";
        }
    }
    public String sendSimpleMailAdmin(Email email) {

        try {

            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();
            String signUpLink = "http://atc-2024-autobotzi-fe-linux-web-app.azurewebsites.net/signUpAdmin \n\n ";

            mailMessage.setFrom(sender);
            mailMessage.setTo(email.getRecipient());
            mailMessage.setText(email.getMessage());
            mailMessage.setSubject(email.getSubject());
            mailMessage.setText(email.getMessage() + "\n\n" + signUpLink);

            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        } catch (Exception e) {
            return "Error while Sending Mail";
        }
    }
}
