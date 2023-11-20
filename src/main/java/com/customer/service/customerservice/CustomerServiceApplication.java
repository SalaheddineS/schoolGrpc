package com.customer.service.customerservice;

import com.customer.entities.Customer;
import com.customer.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import net.devh.boot.grpc.server.service.GrpcService;

@SpringBootApplication
@EnableJpaRepositories(basePackages="com.customer.repository")
@EntityScan(basePackages="com.customer.entities")
@GrpcService
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner start(CustomerRepository customerRepository){
        return args -> {
            customerRepository.save(Customer.builder().name("x").email("x@gmail.com").build());
            customerRepository.save(Customer.builder().name("y").email("y@gmail.com").build());
            customerRepository.save(Customer.builder().name("z").email("z@gmail.com").build());
        };
    }
}
