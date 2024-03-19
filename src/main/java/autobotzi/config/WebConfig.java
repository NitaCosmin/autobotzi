package autobotzi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")  // Add your frontend URL
                .allowedOrigins("http://atc-2024-autobotzi-fe-linux-web-app.azurewebsites.net/")
                .allowedOrigins("https://frontend-jf48yfydc-eduard-ionel-eduards-projects.vercel.app/")
                .allowedOrigins("https://front-autobotzi-c55123365842.herokuapp.com/")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
}
}
