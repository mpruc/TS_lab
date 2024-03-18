package org.example.lista1techsieciowe.repository;

import org.example.lista1techsieciowe.entity.BookDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDetailsRepository extends CrudRepository<BookDetails, Integer> {
}
