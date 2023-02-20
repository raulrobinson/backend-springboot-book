package com.coder.ws.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.coder.ws.model.Book;
import com.coder.ws.repository.BookRepository;
import com.coder.ws.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(path = "${controller.properties.base-path}", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllTutorials(@RequestParam(required = false) String title) {
		try {
			List<Book> books = new ArrayList<>();
			if (title == null)
				books.addAll(bookService.findAll());
			else
				books.addAll(bookService.findByTitleContaining(title));
			if (books.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(books, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*@GetMapping("/books/{id}")
	public ResponseEntity<Book> getTutorialById(@PathVariable("id") long id) {
		List<Book> tutorialData = bookService.findById(id);
		return tutorialData.map(book ->
				new ResponseEntity<>(book, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}*/

	@PostMapping("/books")
	public ResponseEntity<Book> createTutorial(@RequestBody Book book) {
		try {
			Book req = bookService
					.save(new Book(book.getTitle(), book.getDescription(), false));
			return new ResponseEntity<>(req, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*@PutMapping("/books/{id}")
	public ResponseEntity<Book> updateTutorial(@PathVariable("id") long id, @RequestBody Book book) {
		List<Book> tutorialData = bookService.findById(id);
		if (tutorialData.isPresent()) {
			Book req = tutorialData.get();
			req.setTitle(book.getTitle());
			req.setDescription(book.getDescription());
			req.setPublished(book.isPublished());
			return new ResponseEntity<>(bookService.save(req), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}*/

	@DeleteMapping("/books/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			bookService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/books")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		try {
			bookService.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/books/published")
	public ResponseEntity<List<Book>> findByPublished() {
		try {
			List<Book> books = bookService.findByPublished(true);

			if (books.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(books, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
