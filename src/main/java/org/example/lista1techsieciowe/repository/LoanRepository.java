package org.example.lista1techsieciowe.repository;

import org.example.lista1techsieciowe.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for accessing and managing loan entities.
 */
@Repository
public interface LoanRepository extends CrudRepository<Loan, Integer> {
    List<Loan> findByUserId(Integer userId);
}
