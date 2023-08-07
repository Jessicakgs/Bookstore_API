package com.example.Bookstore.DTO;

import jakarta.validation.constraints.NotBlank;

public record CustomerRecordDTO(@NotBlank String firstName, @NotBlank String lastName,@NotBlank String cpf,@NotBlank String phone,@NotBlank String email) {
	
	
}
