package com.customer.repository;

import com.customer.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
}