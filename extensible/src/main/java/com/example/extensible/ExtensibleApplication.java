package com.example.extensible;

import lombok.SneakyThrows;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ExtensibleApplication {

	@SneakyThrows
	public static void main(String[] args) {
		SpringApplication.run(ExtensibleApplication.class, args);
		Thread.currentThread().join();
	}

	@Bean
	CustomerService customerService() {
		CustomerService proxy = ProxyFactory.getProxy(CustomerService.class, (MethodInterceptor) invocation -> {
			if (invocation.getMethod().getName().equals("findById"))
				return new Customer(1, "Andy");
			return null;
		});
		for (var c : proxy.getClass().getInterfaces())
			System.out.println("the interface is " + c);
		return proxy;
	}

	@Bean
	ApplicationRunner runner(CustomerService customerService) {
		return args -> {
			var customer = customerService.findById(2);
			System.out.println("the customer is " + customer);
		};
	}

}

record Customer(Integer id, String name) {
}

interface CustomerService {
	Customer findById(Integer id);
}