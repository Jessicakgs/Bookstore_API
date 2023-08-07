package com.example.Bookstore.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BooksRecordDTO(@NotBlank String name,@NotBlank String author,@NotNull int numPaginas, boolean readyToUse) {
	

}
