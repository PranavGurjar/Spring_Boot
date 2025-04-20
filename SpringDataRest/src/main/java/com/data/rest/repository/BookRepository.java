package com.data.rest.repository;

import com.data.rest.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "mybooks", collectionResourceRel = "mbook")
public interface BookRepository extends JpaRepository<Book, Integer> {

}
