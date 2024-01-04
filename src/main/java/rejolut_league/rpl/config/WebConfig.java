package rejolut_league.rpl.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // registry.addMapping("/*")
        //         .allowedOrigins("http://localhost:3000", "https://f6ad-2405-201-1f-1c98-73-4307-95bf-2ffd.ngrok-free.app") // Add your frontend URL here
        //         .allowedMethods("GET", "POST", "PUT", "DELETE")
        //         .allowedHeaders("*")
        //         .allowedOriginPatterns("*");
        registry.addMapping("/**")
        .allowedOrigins("http://localhost:3000", "https://f6ad-2405-201-1f-1c98-73-4307-95bf-2ffd.ngrok-free.app")
        .allowedMethods("GET", "POST", "PUT", "DELETE")
        .allowedHeaders("*")
        .allowedOriginPatterns("*")
        .allowCredentials(true)
        .maxAge(3600);
    }
}
