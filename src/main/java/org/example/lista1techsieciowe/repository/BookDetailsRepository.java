package org.example.lista1techsieciowe.repository;

import org.example.lista1techsieciowe.entity.BookDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing and managing book details entities.
 */
@Repository
public interface BookDetailsRepository extends CrudRepository<BookDetails, Integer> {
}
