package com.coder.ws.service.impl;

import com.coder.ws.dto.BookRequestDTO;
import com.coder.ws.model.Book;
import com.coder.ws.repository.BookRepository;
import com.coder.ws.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findBookAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book createBook(BookRequestDTO book) {
        Book bookDB = bookRepository.findByCodebook(book.getCodebook());
        if (bookDB != null) {
            return Book.builder()
                    .id(bookDB.getId())
                    .codebook(book.getCodebook())
                    .title(bookDB.getTitle())
                    .description(bookDB.getDescription())
                    .published(bookDB.isPublished())
                    .build();
        }
        Book bookSave = Book.builder()
                .codebook(book.getCodebook())
                .title(book.getTitle())
                .description(book.getDescription())
                .published(book.isPublished())
                .build();
        bookDB = bookRepository.save(bookSave);
        return bookDB;
    }

    @Override
    public Book updateBook(Long id, BookRequestDTO book) {
        Book bookDB = bookRepository.getById(id);
        if (bookDB == null) {
            return null;
        }
        Book bookUpdate = Book.builder()
                .id(bookDB.getId())
                .codebook(book.getCodebook())
                .title(book.getTitle())
                .description(book.getDescription())
                .published(book.isPublished())
                .build();
        bookRepository.save(bookUpdate);
        return bookUpdate;
    }

    @Override
    public Book deleteById(Long id) {
        Book bookDelete = getBookById(id);
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        }
        return bookDelete;
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book getByCodebook(String codebook) {
        return bookRepository.findByCodebook(codebook);
    }

}
