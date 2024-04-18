package org.example.lista1techsieciowe.repository;

import org.example.lista1techsieciowe.entity.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing and managing review entities.
 */
@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer> {
}
