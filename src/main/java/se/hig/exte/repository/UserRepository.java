package se.hig.exte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import se.hig.exte.model.User;

/**
 * An interface used for database operations on {@link User} objects. References
 * to this interface are automatically given an implementation by Spring Boot
 * when using the {@code Autowired} annotation.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

	/**
	 * Fetches the {@link User} object with the given ID from the database.
	 * 
	 * @param id The ID of the {@link User} object to fetch.
	 * @return The {@link User} object representing the database record with the
	 *         specified ID.
	 */
	User findById(int id);

	List<User> findByName(String name);
}
