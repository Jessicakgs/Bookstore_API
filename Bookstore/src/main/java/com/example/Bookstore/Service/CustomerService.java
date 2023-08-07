package com.example.Bookstore.Service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bookstore.DTO.CustomerRecordDTO;
import com.example.Bookstore.Model.CustomerModel;
import com.example.Bookstore.Repository.CustomerRepository;


@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;

	public CustomerModel create(CustomerRecordDTO customerRecordDTO) {
		
		CustomerModel customer = new CustomerModel();

		BeanUtils.copyProperties(customerRecordDTO, customer);
		
		customerRepository.save(customer);
		
		return customer;
	}
	
	public CustomerModel alter(CustomerRecordDTO customerRecordDTO, Optional<CustomerModel> customer0) {
		
		CustomerModel customer = customer0.get();

		BeanUtils.copyProperties(customerRecordDTO, customer);
		
		customerRepository.save(customer);
		
		return customer;
	}

}
