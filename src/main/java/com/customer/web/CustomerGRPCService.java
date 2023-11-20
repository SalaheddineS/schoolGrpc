package com.customer.web;


import com.customer.entities.Customer;
import com.customer.mappers.CustomerMapper;
import com.customer.repository.CustomerRepository;
import com.customer.stub.CustomerServiceGrpc;
import com.customer.stub.CustomerServiceOuterClass;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CustomerGRPCService  extends CustomerServiceGrpc.CustomerServiceImplBase {
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;
    @Override
    public void getAllCustomers(CustomerServiceOuterClass.GetAllCustomersRequest request, StreamObserver<CustomerServiceOuterClass.GetCustomersResponse> responseObserver) {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerServiceOuterClass.Customer> customersResponselist =
                customers.stream()
                        .map(customerMapper::fromCustomer).collect(Collectors.toList());
        CustomerServiceOuterClass.GetCustomersResponse customersResponse=
                CustomerServiceOuterClass.GetCustomersResponse.newBuilder()
                        .addAllCustomers(customersResponselist)
                        .build();
        responseObserver.onNext(customersResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void getCustomerById(CustomerServiceOuterClass.GetCustomerByIdRequest request, StreamObserver<CustomerServiceOuterClass.GetCustomerByIdResponse> responseObserver) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new NoSuchElementException("Customer not found with ID: " + request.getCustomerId()));

        CustomerServiceOuterClass.GetCustomerByIdResponse customerResponse =
                CustomerServiceOuterClass.GetCustomerByIdResponse.newBuilder()
                        .setCustomer(customerMapper.fromCustomer(customer))
                        .build();

        responseObserver.onNext(customerResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void saveCustomer(CustomerServiceOuterClass.SaveCustomerRequest request, StreamObserver<CustomerServiceOuterClass.SaveCustomerResponse> responseObserver) {
        Customer customer = customerMapper.fromGrpcCustomerRequest(request.getCustomer());

        customerRepository.save(customer);

        CustomerServiceOuterClass.SaveCustomerResponse saveCustomerResponse =
                CustomerServiceOuterClass.SaveCustomerResponse.newBuilder()
                        .setCustomer(customerMapper.fromCustomer(customer))
                        .build();

        responseObserver.onNext(saveCustomerResponse);
        responseObserver.onCompleted();
    }

}
