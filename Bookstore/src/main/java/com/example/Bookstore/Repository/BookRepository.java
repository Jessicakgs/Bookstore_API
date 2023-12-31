package com.example.Bookstore.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Bookstore.Model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

	
}
