package com.example.Bookstore.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bookstore.Model.Customer;
import com.example.Bookstore.Repository.CustomerRepository;
import com.example.Bookstore.Response.CustomerResponse;
import com.example.Bookstore.Resquest.CustomerRequest;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setEmail(customerRequest.getEmail());

        Customer createdCustomer = customerRepository.save(customer);

        CustomerResponse response = new CustomerResponse();
        response.setFirstName(createdCustomer.getFirstName());
        response.setLastName(createdCustomer.getLastName());
        response.setEmail(createdCustomer.getEmail());

        return response;
    }

}
