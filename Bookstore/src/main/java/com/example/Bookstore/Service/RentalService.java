package com.example.Bookstore.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bookstore.Model.Book;
import com.example.Bookstore.Model.Customer;
import com.example.Bookstore.Model.Rental;
import com.example.Bookstore.Repository.BookRepository;
import com.example.Bookstore.Repository.CustomerRepository;
import com.example.Bookstore.Repository.RentalRepository;
import com.example.Bookstore.Response.RentalResponse;
import com.example.Bookstore.Resquest.RentalRequest;
import com.example.Bookstore.exception.OutOfStockException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;
    
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private CustomerService customerService;

    public RentalResponse createRental(RentalRequest rentalRequest) {
        Customer customer = customerRepository.findById(rentalRequest.getCustomerId())
                .orElseThrow(EntityNotFoundException::new);
        
        Book book = bookRepository.findById(rentalRequest.getBookId())
                .orElseThrow(EntityNotFoundException::new);
        
        if (book.getStockQuantity() > 0) {
            book.setStockQuantity(book.getStockQuantity() - 1);            
            bookRepository.save(book);
        }else {
            throw new OutOfStockException("Não há estoque disponível para este livro.");
        }

        Rental rental = new Rental();
        rental.setCustomer(customer);
        rental.setBook(book);
        
        Rental createdRental = rentalRepository.save(rental);

        RentalResponse rentalResponse = new RentalResponse();
        rentalResponse.setCustomer(customerService.toCustomerResponse(createdRental.getCustomer()));
        rentalResponse.setBook(bookService.toBookResponse(createdRental.getBook()));
        rentalResponse.setRentedAt(createdRental.getRentalDate());
        rentalResponse.setReturnedAt(createdRental.getReturnDate());

        return rentalResponse;
    }
    
	

	
	
}
