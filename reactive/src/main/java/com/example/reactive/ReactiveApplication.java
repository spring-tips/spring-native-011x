package com.example.reactive;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
public class ReactiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveApplication.class, args);
	}

	@Bean
	ApplicationRunner runner(CustomerRepository repository) {
		return args -> Flux
			.just("Andy", "Madhura", "Jürgen", "Yuxin", "Olga", "Violetta", "Stéphane", "Dr Syer")
			.map(name -> new Customer(null, name))
			.flatMap(repository::save)
			.subscribe(System.out::println);
	}

	@Bean
	RouterFunction<ServerResponse> routes(CustomerRepository repository) {
		return route()
			.GET("/customers", request -> ServerResponse.ok().body(repository.findAll(), Customer.class))
			.build();
	}
}

record Customer(@Id Integer id, String name) {
}

interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
}