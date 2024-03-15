package autobotzi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AutobotziApplication {


    public static void main(String[] args) {
        SpringApplication.run(AutobotziApplication.class, args);
    }


}
