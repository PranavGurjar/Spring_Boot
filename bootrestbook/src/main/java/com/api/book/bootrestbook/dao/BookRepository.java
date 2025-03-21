package com.api.book.bootrestbook.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.book.bootrestbook.entities.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer>{
    public Book findById(int id);
    // Optional<Book> findById(Integer id); 
}
