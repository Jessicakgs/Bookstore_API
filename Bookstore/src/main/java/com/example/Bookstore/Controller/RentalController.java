package com.example.Bookstore.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bookstore.Response.RentalResponse;
import com.example.Bookstore.Resquest.RentalRequest;
import com.example.Bookstore.Service.RentalService;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @PostMapping
    public ResponseEntity<RentalResponse> createRental(@RequestBody RentalRequest rentalRequest) {
        RentalResponse response = rentalService.createRental(rentalRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
