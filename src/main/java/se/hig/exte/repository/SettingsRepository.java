package se.hig.exte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.hig.exte.model.Settings;

/**
 * An interface used for database operations on {@link Settings} objects.
 * References to this interface are automatically given an implementation by
 * Spring Boot when using the {@code Autowired} annotation.
 */
@Repository
public interface SettingsRepository extends JpaRepository<Settings, Integer> {

	/**
	 * Fetches the {@link Settings} object with the given ID from the database.
	 * 
	 * @param id The ID of the {@link Settings} object to fetch.
	 * @return The {@link Settings} object representing the database record with the
	 *         specified ID.
	 */
	Settings findById(int id);

	/**
	 * Fetches the newest {@link Settings} object from the database.
	 * 
	 * @return The {@link Settings} object representing the database record that was
	 *         most recently added.
	 */
	Settings findFirstByOrderByCreatedDesc();

	/**
	 * Fetches all {@link Settings} objects from the database and returns them
	 * sorted by creation date with the most recently added {@link Settings} first.
	 * 
	 * @return A {@code List} containing all {@link Settings} objects sorted by
	 *         creation date with the most recently added {@link Settings} first.
	 */
	List<Settings> findAllByOrderByCreatedDesc();

	/**
	 * Fetches the ten most newly created {@link Settings} objects from the database
	 * and returns them sorted by creation date with the most recently added
	 * {@link Settings} first.
	 * 
	 * @return A {@code List} containing the ten most newly created {@link Settings}
	 *         objects sorted by creation date with the most recently added
	 *         {@link Settings} first.
	 */
	List<Settings> findTop10ByOrderByCreatedDesc();
}
