package com.coder.ws.service;

import com.coder.ws.dto.BookRequestDTO;
import com.coder.ws.model.Book;

import java.util.List;

public interface BookService {
    List<Book> findBookAll();
    Book createBook(BookRequestDTO book);
    Book getBookById(Long id);
    Book getByCodebook(String codebook);
    Book updateBook(Long id, BookRequestDTO book);
    Book deleteById(Long id);
}
