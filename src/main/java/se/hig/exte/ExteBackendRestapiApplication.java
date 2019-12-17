package se.hig.exte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {
		"se.hig.exte"
})
@EnableScheduling
public class ExteBackendRestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExteBackendRestapiApplication.class, args);
	}

}
