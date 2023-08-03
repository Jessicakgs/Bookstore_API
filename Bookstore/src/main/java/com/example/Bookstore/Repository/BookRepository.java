package com.example.Bookstore.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Bookstore.Model.BookModel;


@Repository
public interface BookRepository extends JpaRepository<BookModel, UUID>{

	
}
