package mz.ca.cmm.gestaoviatura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class GestaoviaturaApplication {

	public static void main(String[] args) {
		
		//sYSTEM.out.println(new BCryptPasswordEncoder().encode("1234"));
		
		SpringApplication.run(GestaoviaturaApplication.class, args);
		
	}

}
