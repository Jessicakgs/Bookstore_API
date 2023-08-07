package com.example.Bookstore.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bookstore.DTO.CustomerRecordDTO;
import com.example.Bookstore.Model.CustomerModel;
import com.example.Bookstore.Repository.CustomerRepository;
import com.example.Bookstore.Service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CustomerService customerService;

	@PostMapping
	public ResponseEntity<CustomerModel> createCustomer(@RequestBody @Valid CustomerRecordDTO customerRecordDTO){
		
		CustomerModel customerModel = customerService.create(customerRecordDTO);
				
		return ResponseEntity.status(HttpStatus.CREATED).body(customerModel);
	}
	
	@GetMapping
	public ResponseEntity<List<CustomerModel>> GetCustomers (){
		
		List<CustomerModel> CustomerList = customerRepository.findAll();

	
		return ResponseEntity.status(HttpStatus.OK).body(CustomerList);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> GetIdCustomer(@PathVariable(value = "id") long id) {
		
		Optional<CustomerModel> product = customerRepository.findById(id);
		if(product.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found!");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(product.get()); 
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> AlterProduct(@PathVariable(value = "id") long id, @RequestBody @Valid CustomerRecordDTO customerRecordDTO ) {
		
		Optional<CustomerModel> customer = customerRepository.findById(id);
		if(customer.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found!");
		}
		customerService.alter(customerRecordDTO, customer);
		
		return ResponseEntity.status(HttpStatus.OK).body(customer); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") long id) {
		
		Optional<CustomerModel> customer = customerRepository.findById(id);
		if(customer.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found!");
		}
		
		customerRepository.delete(customer.get());
		return ResponseEntity.status(HttpStatus.OK).body("Customer deleted successfully."); 
	}
	
}
