package com.example.Bookstore.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bookstore.Model.Book;
import com.example.Bookstore.Repository.BookRepository;
import com.example.Bookstore.Response.BookResponse;
import com.example.Bookstore.Resquest.BookRequest;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookResponse createBook(BookRequest bookRequest) {
        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setIsbn(bookRequest.getIsbn());

        Book createdBook = bookRepository.save(book);

        BookResponse bookResponse = new BookResponse();
        bookResponse.setTitle(createdBook.getTitle());
        bookResponse.setAuthor(createdBook.getAuthor());
        bookResponse.setIsbn(createdBook.getIsbn());

        return bookResponse;
    }
    
    public BookResponse getBookById(Long id) {
    	Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));

        return toBookResponse(book);
    }
    
    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(this::toBookResponse)
                .collect(Collectors.toList());
    }

    
    public BookResponse toBookResponse(Book book) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setTitle(book.getTitle());
        bookResponse.setAuthor(book.getAuthor());
        bookResponse.setIsbn(book.getIsbn());

        return bookResponse;
    }
    
    public BookResponse updateBook(Long id, BookRequest bookRequest) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));

        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setIsbn(bookRequest.getIsbn());
        book.setStockQuantity(bookRequest.getStockQuantity());

        Book updatedBook = bookRepository.save(book);
        return toBookResponse(updatedBook);
    }
    
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        bookRepository.delete(book);
    }



}

