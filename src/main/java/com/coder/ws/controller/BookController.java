package com.coder.ws.controller;

import java.util.*;

import com.coder.ws.dto.BookRequestDTO;
import com.coder.ws.dto.BookResponseDTO;
import com.coder.ws.model.Book;
import com.coder.ws.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "${controller.properties.base-path}", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("/books")
	public ResponseEntity<BookResponseDTO> getAllBooks() {
		try {
			List<Book> books = bookService.findBookAll();
			if (books == null) {
				return new ResponseEntity<>(BookResponseDTO.builder()
						.code(200L)
						.message("404 Not Found")
						.data(null)
						.build(), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(BookResponseDTO.builder()
					.code(200L)
					.message("200 OK")
					.data(books)
					.build(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(BookResponseDTO.builder()
					.code(503L)
					.message("503 Server Internal Error")
					.data(null)
					.build(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/books/codebook")
	public ResponseEntity<BookResponseDTO> getBookByCodebook(
			@RequestParam("codebook") String codebook) {
		try {
			Book bookData = bookService.getByCodebook(codebook);
			if (bookData == null)
				return new ResponseEntity<>(BookResponseDTO.builder()
						.code(404L)
						.message("404 Not Found")
						.data(null)
						.build(), HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(BookResponseDTO.builder()
					.code(200L)
					.message("200 OK")
					.data(bookData)
					.build(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(BookResponseDTO.builder()
					.code(503L)
					.message("503 Server Internal Error")
					.data(null)
					.build(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/books/id")
	public ResponseEntity<BookResponseDTO> findById(
			@RequestParam(value = "id", required = true) Long id) {
		try {
			Book book = bookService.getBookById(id);
			if (book == null) {
				return new ResponseEntity<>(BookResponseDTO.builder()
						.code(404L)
						.message("404 Not Found")
						.data(null)
						.build(), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(BookResponseDTO.builder()
					.code(200L)
					.message("200 OK")
					.data(book)
					.build(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(BookResponseDTO.builder()
					.code(503L)
					.message("503 Server Internal Error")
					.data(null)
					.build(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/books")
	public ResponseEntity<BookResponseDTO> createBook(
			@RequestBody BookRequestDTO book) {
		try {
			Book req = bookService.createBook(book);
			return new ResponseEntity<>(BookResponseDTO.builder()
					.code(200L)
					.message("200 OK")
					.data(req)
					.build(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(BookResponseDTO.builder()
					.code(503L)
					.message("503 Server Internal Error")
					.data(null)
					.build(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/books")
	public ResponseEntity<BookResponseDTO> updateBook(
			@RequestParam(value = "id", required = true) Long id,
			@RequestBody BookRequestDTO book) {
		try {
			Book req = bookService.updateBook(id, book);
			return new ResponseEntity<>(BookResponseDTO.builder()
					.code(200L)
					.message("200 OK")
					.data(req)
					.build(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(BookResponseDTO.builder()
					.code(404L)
					.message("404 Not Found")
					.data(null)
					.build(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/books")
	public ResponseEntity<BookResponseDTO> deleteBook(
			@RequestParam(value = "id", required = true) Long id) {
		try {
			Book req = bookService.deleteById(id);
			return new ResponseEntity<>(BookResponseDTO.builder()
					.code(200L)
					.message("200 OK")
					.data(req)
					.build(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(BookResponseDTO.builder()
					.code(404L)
					.message("404 Not Found")
					.data(null)
					.build(), HttpStatus.NOT_FOUND);
		}
	}

}
