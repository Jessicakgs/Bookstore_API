package com.example.Bookstore.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Bookstore.Model.CustomerModel;


@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long>{

	
}
