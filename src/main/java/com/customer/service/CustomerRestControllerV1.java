package com.customer.service;

import com.customer.entities.Customer;
import com.customer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
public class CustomerRestControllerV1 {

    private CustomerRepository customerRepository;


    public List<Customer> customerList(){
        return customerRepository.findAll();
    }


    public Customer customerById( Long id){
        return customerRepository.findById(id).get();
    }


    public Customer saveCustomer( Customer customer){
        customerRepository.save(customer);
        return customer;
    }
}