package se.hig.exte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.hig.exte.model.Academy;

/**
 * An interface used for database operations on {@link Academy} objects.
 * References to this interface are automatically given an implementation by Spring Boot when using
 * the {@code Autowired} annotation.
 */
@Repository
public interface AcademyRepository extends JpaRepository<Academy, Integer> {

	/**
	 * Fetches the {@link Academy} object with the given ID from the database.
	 * @param id The ID of the {@link Academy} object to fetch.
	 * @return The {@link Academy} object representing the database record with the specified ID.
	 */
	Academy findById(int id);

}
