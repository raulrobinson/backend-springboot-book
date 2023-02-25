package com.coder.ws.repository;

import com.coder.ws.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
  Book findByCodebook(String codebook);
}
