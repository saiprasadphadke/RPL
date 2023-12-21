package rejolut_league.rpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RplApplication {

	public static void main(String[] args) {
		SpringApplication.run(RplApplication.class, args);
	}

	@GetMapping("/")
	public String greeting(@RequestParam(value = "name", defaultValue = "User") String name) {
		return ("Hello " + name);
	}

}
