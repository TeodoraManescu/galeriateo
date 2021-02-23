package ro.teodoramanescu.galeriateo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class GaleriateoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GaleriateoApplication.class, args);
	}

}
