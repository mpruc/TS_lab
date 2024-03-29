package org.example.lista1techsieciowe.repository;
import org.example.lista1techsieciowe.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> { // pierwsze to co mapujemy, drugie to typ id
}
