package musify.collection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "musify.collection")
public class MusifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusifyApplication.class, args);
	}
}
