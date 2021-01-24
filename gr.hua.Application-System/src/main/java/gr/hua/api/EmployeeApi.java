package gr.hua.api;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import gr.hua.entity.Contact;
import gr.hua.entity.Employee;
import reactor.core.publisher.Flux;

@Service
public class EmployeeApi {
	
	WebClient webClient = WebClient.builder()
	        .baseUrl("http://localhost:8080/api/employees")
	        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	        .build();
	
	public Flux<Employee> test() {
		return webClient.get().uri("/profile").retrieve().bodyToFlux(Employee.class);
	}
	
}
