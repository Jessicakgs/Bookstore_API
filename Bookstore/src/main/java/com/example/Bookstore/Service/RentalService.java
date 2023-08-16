package com.example.Bookstore.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
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
        rentalResponse.setRentedAt(createdRental.getRentedAt());
        rentalResponse.setReturnedAt(createdRental.getReturnedAt());

        return rentalResponse;
    }
    
    @Transactional(readOnly = true)
    public RentalResponse getRentalById(Long id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rental not found with id: " + id));
        return toRentalResponse(rental);
    }

    @Transactional(readOnly = true)
    public List<RentalResponse> getAllRentals() {
        List<Rental> rentals = rentalRepository.findAll();
        return rentals.stream()
                .map(this::toRentalResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteRental(Long id) {
        rentalRepository.deleteById(id);
    }
    
    @Transactional
    public RentalResponse returnRental(Long id) {
        Rental existingRental = rentalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rental not found with id: " + id));

        existingRental.setReturnedAt(LocalDateTime.now());
        Rental updatedRental = rentalRepository.save(existingRental);
                   
        bookService.returnBook(existingRental);
 
        return toRentalResponse(updatedRental);
    }
    
    public RentalResponse toRentalResponse(Rental rental) {
    	RentalResponse rentalResponse = new RentalResponse();
    	rentalResponse.setCustomer(customerService.toCustomerResponse(rental.getCustomer()));
    	rentalResponse.setBook(bookService.toBookResponse(rental.getBook()));
    	rentalResponse.setRentedAt(rental.getRentedAt());
    	rentalResponse.setReturnedAt(rental.getReturnedAt());
    	

        return rentalResponse;
    }

	
	
}
