package se.hig.exte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.hig.exte.model.Course;
import se.hig.exte.model.Subject;

/**
 * An interface used for database operations on {@link Course} objects.
 * References to this interface are automatically given an implementation by
 * Spring Boot when using the {@code Autowired} annotation.
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

	/**
	 * Fetches the {@link Course} object with the given ID from the database.
	 * 
	 * @param id The ID of the {@link Course} object to fetch.
	 * @return The {@link Course} object representing the database record with the
	 *         specified ID.
	 */
	Course findById(int id);

	/**
	 * Fetches all {@link Course} objects which belong to the specified
	 * {@link Subject} from the database.
	 * 
	 * @param subjectId The ID of the {@link Subject}.
	 * @return A {@code List} containing all {@link Course}s belonging to the
	 *         {@link Subject} with the specified ID.
	 */
	List<Course> findBySubjectIdAndUnpublishedFalse(int subjectId);

	/**
	 * Fetches all {@link Course} objects which belong to the specified
	 * {@link Subject} from the database.
	 * 
	 * @param subjectId The ID of the {@link Subject}.
	 * @return A {@code List} containing all {@link Course}s belonging to the
	 *         {@link Subject} with the specified ID.
	 */
	List<Course> findBySubjectId(int subjectId);

	/**
	 * Fetches all {@link Course} objects from the database where:
	 * <ul>
	 * <li>its name attribute contains the string passed in as the name parameter,
	 * or</li>
	 * <li>its courseCode attribute contains the string passed in as the courseCode
	 * parameter</li>
	 * </ul>
	 * 
	 * @param name       The name {@code String} that the fetched {@link Course}
	 *                   objects should contain.
	 * @param courseCode The course code {@code String} that the fetched
	 *                   {@link Course} objects should contain.
	 * @return A {@code List} containing all {@link Course} objects whose name
	 *         attribute contains the passed in name parameter and all
	 *         {@link Course} objects whose course code attribute contains the
	 *         passed in courseCode parameter.
	 */
	List<Course> findByNameContainingOrCourseCodeContaining(String name, String courseCode);

	/**
	 * Fetches all published {@link Course} objects from the database where:
	 * <ul>
	 * <li>its name attribute contains the string passed in as the name parameter,
	 * or</li>
	 * <li>its courseCode attribute contains the string passed in as the courseCode
	 * parameter</li>
	 * </ul>
	 * 
	 * @param name       The name {@code String} that the fetched {@link Course}
	 *                   objects should contain.
	 * @param courseCode The course code {@code String} that the fetched
	 *                   {@link Course} objects should contain.
	 * @return A {@code List} containing all {@link Course} objects whose name
	 *         attribute contains the passed in name parameter and all
	 *         {@link Course} objects whose course code attribute contains the
	 *         passed in courseCode parameter.
	 */
	List<Course> findByUnpublishedFalseAndNameContainingOrUnpublishedFalseAndCourseCodeContaining(String name,
			String courseCode);

	/**
	 * Fetches all {@link Course} objects with the unpublished value set to true.
	 * 
	 * @return A {@link List} containing all {@link Course}s with the unpublished
	 *         value set to true.
	 */
	List<Course> findByUnpublishedTrue();

	/**
	 * Fetches all {@link Course} objects with the unpublished value set to false.
	 * 
	 * @return A {@link List} containing all {@link Course}s with the unpublished
	 *         value set to false.
	 */
	List<Course> findByUnpublishedFalse();

	List<Course> findByUnpublishedFalseAndSubjectIdEquals(int id);

}