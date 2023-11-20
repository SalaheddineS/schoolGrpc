package com.customer.mappers;

import com.customer.entities.Customer;
import com.customer.stub.CustomerServiceOuterClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    private final ModelMapper modelMapper;

    public CustomerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CustomerServiceOuterClass.Customer fromCustomer(Customer customer) {
        return modelMapper.map(customer, CustomerServiceOuterClass.Customer.class);
    }

    public Customer fromGrpcCustomerRequest(CustomerServiceOuterClass.CustomerRequest customerRequest) {
        return modelMapper.map(customerRequest, Customer.class);
    }
}
