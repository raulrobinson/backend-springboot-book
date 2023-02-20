package com.coder.ws.service;

import com.coder.ws.model.Book;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();

    List<Book> findByTitleContaining(String title);

    List<Book> findById(long id);

    Book save(Book req);

    Book deleteById(long id);

    Book deleteAll();

    List<Book> findByPublished(boolean b);
}
