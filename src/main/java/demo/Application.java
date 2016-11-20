package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
class Application {


	@Bean
	public RestTemplate restTemplate(){
		RestTemplate restTemplate = new RestTemplate();
		final List<HttpMessageConverter<?>> listHttpMessageConverters = new ArrayList<HttpMessageConverter<?>>();

		listHttpMessageConverters.add(new GsonHttpMessageConverter());
		listHttpMessageConverters.add(new FormHttpMessageConverter());
		listHttpMessageConverters.add(new StringHttpMessageConverter());
		restTemplate.setMessageConverters(listHttpMessageConverters);

		return restTemplate;
	}

	public static void main(String args[]){
		SpringApplication.run(Application.class, args);
	}
}