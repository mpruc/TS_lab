package org.example.lista1techsieciowe.repository;
import org.example.lista1techsieciowe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing and managing user entities.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
