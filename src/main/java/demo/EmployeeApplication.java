package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
class EmployeeApplication {


	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	public static void main(String args[]){
		SpringApplication.run(EmployeeApplication.class, args);
	}
}