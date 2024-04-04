package org.example.lista1techsieciowe.repository;

import org.example.lista1techsieciowe.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends CrudRepository<Login, Integer> {
    Optional<Login> findByUsername(String username);
}
