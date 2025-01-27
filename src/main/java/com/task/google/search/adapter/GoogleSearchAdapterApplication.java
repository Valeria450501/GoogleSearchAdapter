package com.task.google.search.adapter;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Google Search Adapter",
				description = "REST interface to call googleapi"
		)
)
public class GoogleSearchAdapterApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoogleSearchAdapterApplication.class, args);
	}

}
