package org.example.lista1techsieciowe.repository;

import org.example.lista1techsieciowe.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
}
