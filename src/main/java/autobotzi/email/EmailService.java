package autobotzi.email;

public interface EmailService {
    String sendSimpleMail(Email email);
    String sendSimpleMailAdmin(Email email);

}
