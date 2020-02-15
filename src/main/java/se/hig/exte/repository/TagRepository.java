package se.hig.exte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.hig.exte.model.Tag;

/**
 * An interface used for database operations on {@link Tag} objects.
 * References to this interface are automatically given an implementation by
 * Spring Boot when using the {@code Autowired} annotation.
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

	/**
	 * Fetches the {@link Tag} object with the given ID from the database.
	 * 
	 * @param id The ID of the {@link Tag} object to fetch.
	 * @return The {@link Tag} object representing the database record with the
	 *         specified ID.
	 */
	Tag findById(int id);

}