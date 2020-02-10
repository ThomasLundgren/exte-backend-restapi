package se.hig.exte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = { "se.hig.exte" }, exclude = { HibernateJpaAutoConfiguration.class })
@EnableScheduling
public class ExteBackendRestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExteBackendRestapiApplication.class, args);
	}

}
