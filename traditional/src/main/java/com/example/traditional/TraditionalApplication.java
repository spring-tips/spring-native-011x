package com.example.traditional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;
import java.util.List;

@SpringBootApplication
public class TraditionalApplication {

	public static void main(String[] args) {
		SpringApplication.run(TraditionalApplication.class, args);
	}
}

@Controller
@ResponseBody
class CustomerRestController {

	private final CustomerRepository customerRepository;

	CustomerRestController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@GetMapping("/customers")
	Collection<Customer> customers() {
		return this.customerRepository.findAll();
	}
}

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
class Customer {

	@Id
	@GeneratedValue
	private Integer id;
	private String name;
}

interface CustomerRepository extends JpaRepository<Customer, Integer> {
}