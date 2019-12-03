package se.hig.exte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"se.hig.exte"
})
public class ExteBackendRestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExteBackendRestapiApplication.class, args);
	}

}
