package com.example.Bookstore.Controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bookstore.DTO.BooksRecordDTO;
import com.example.Bookstore.Model.BookModel;
import com.example.Bookstore.Repository.BookRepository;

import jakarta.validation.Valid;

@RestController 
public class BookController {
	
	@Autowired
	BookRepository bookRepository;

	@PostMapping("/books")
	public ResponseEntity<BookModel> createBook(@RequestBody @Valid BooksRecordDTO booksRecordDTO){
		
		BookModel bookModel = new BookModel();
		
		BeanUtils.copyProperties(booksRecordDTO, bookModel);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(bookRepository.save(bookModel));
		
	}
	
	
}
