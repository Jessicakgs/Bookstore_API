package com.example.Bookstore.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bookstore.Model.Book;
import com.example.Bookstore.Repository.BookRepository;
import com.example.Bookstore.Response.BookResponse;
import com.example.Bookstore.Resquest.BookRequest;

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

}

