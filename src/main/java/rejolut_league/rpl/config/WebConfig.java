package rejolut_league.rpl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            // .allowedOrigins("http://192.168.29.35:3000", "https://bee0-2405-201-1f-1079-c3d-9433-6da8-c8c4.ngrok-free.app", "https://rpl.rejolut.dev")
            .allowedOrigins( "https://rpl.rejolut.dev")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true)
            // .allowedOriginPatterns("https://*")
            .maxAge(3600);
    }
}
