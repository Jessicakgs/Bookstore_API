package com.example.Bookstore.Response;

import java.time.LocalDateTime;

public class RentalResponse {

	private CustomerResponse customer;
    private BookResponse book;
    private LocalDateTime rentedAt;
    private LocalDateTime returnedAt;

	public CustomerResponse getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerResponse customer) {
		this.customer = customer;
	}
	public BookResponse getBook() {
		return book;
	}
	public void setBook(BookResponse book) {
		this.book = book;
	}
	public LocalDateTime getRentedAt() {
		return rentedAt;
	}
	public void setRentedAt(LocalDateTime rentedAt) {
		this.rentedAt = rentedAt;
	}
	public LocalDateTime getReturnedAt() {
		return returnedAt;
	}
	public void setReturnedAt(LocalDateTime returnedAt) {
		this.returnedAt = returnedAt;
	}
}
