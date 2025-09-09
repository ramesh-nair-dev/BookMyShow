package bookMyShow.demo.Repository;

import bookMyShow.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User , Integer> {
    Optional<User> findByEmail(String email);
    User save(User entity);
}

/**
 * UserRepository is an interface that extends JpaRepository to provide CRUD operations for User entities.
 * It includes a method to find a User by their email address.
 * This interface allows for easy interaction with the database without needing to write boilerplate code.
 *  - The JpaRepository provides methods like save, findById, delete, and more, which can be used directly.
 *  - The findByEmail method is a custom query method that retrieves a User based on their email address.
 * If we want to write complex queries, we can use the @Query annotation to define custom queries.
 */
