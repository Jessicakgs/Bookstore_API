package com.example.Bookstore.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        RentalResponse rentalResponse = rentalService.createRental(rentalRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(rentalResponse);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<RentalResponse> getRentalById(@PathVariable Long id) {
        RentalResponse rentalResponse = rentalService.getRentalById(id);
        return ResponseEntity.ok(rentalResponse);
    }

    @GetMapping
    public ResponseEntity<List<RentalResponse>> getAllRentals() {
        List<RentalResponse> responseList = rentalService.getAllRentals();
        return ResponseEntity.ok(responseList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRental(@PathVariable Long id) {
        rentalService.deleteRental(id);
        return ResponseEntity.noContent().build();
    }
    
    @PatchMapping("/{id}/return")
    public ResponseEntity<RentalResponse> returnRental(@PathVariable Long id) {
        RentalResponse rentalResponse = rentalService.returnRental(id);
        return ResponseEntity.ok(rentalResponse);
    }
}
