package com.example.Bookstore.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bookstore.Model.Book;
import com.example.Bookstore.Model.Customer;
import com.example.Bookstore.Model.Rental;
import com.example.Bookstore.Repository.BookRepository;
import com.example.Bookstore.Repository.CustomerRepository;
import com.example.Bookstore.Repository.RentalRepository;
import com.example.Bookstore.Response.BookResponse;
import com.example.Bookstore.Response.CustomerResponse;
import com.example.Bookstore.Response.RentalResponse;
import com.example.Bookstore.Resquest.RentalRequest;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;
    
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookRepository bookRepository;

    public RentalResponse createRental(RentalRequest rentalRequest) {
        Customer customer = customerRepository.findById(rentalRequest.getCustomerId())
                .orElseThrow(EntityNotFoundException::new);
        
        Book book = bookRepository.findById(rentalRequest.getBookId())
                .orElseThrow(EntityNotFoundException::new);

        Rental rental = new Rental();
        rental.setCustomer(customer);
        rental.setBook(book);
        
        Rental createdRental = rentalRepository.save(rental);

        RentalResponse rentalResponse = new RentalResponse();
        rentalResponse.setCustomer(toCustomerResponse(createdRental.getCustomer()));
        rentalResponse.setBook(toBookResponse(createdRental.getBook()));
        rentalResponse.setRentedAt(createdRental.getRentalDate());
        rentalResponse.setReturnedAt(createdRental.getReturnDate());

        return rentalResponse;
    }
    
	
	public static CustomerResponse toCustomerResponse(Customer customer) {
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setFirstName(customer.getFirstName());
        customerResponse.setLastName(customer.getLastName());
        customerResponse.setEmail(customer.getEmail());

        return customerResponse;
    }
	
	public static BookResponse toBookResponse(Book book) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setTitle(book.getTitle());
        bookResponse.setAuthor(book.getAuthor());
        bookResponse.setIsbn(book.getIsbn());

        return bookResponse;
    }
}
