package com.example.Bookstore.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Bookstore.Model.Rental;


@Repository
public interface RentalRepository extends JpaRepository<Rental, Long>{

	
}
