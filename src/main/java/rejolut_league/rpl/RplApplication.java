package rejolut_league.rpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import rejolut_league.rpl.filters.AuthFilter;

@SpringBootApplication
@RestController
public class RplApplication {

	public static void main(String[] args) {
		SpringApplication.run(RplApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<AuthFilter> filterRegistrationBean() {
		FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();

		AuthFilter authFilter = new AuthFilter();

		registrationBean.setFilter(authFilter);
		registrationBean.addUrlPatterns("/bid/submit");

		return registrationBean;
	}

	@GetMapping("/")
	public String greeting(@RequestParam(value = "name", defaultValue = "User") String name) {
		return ("Hello " + name);
	}

}
