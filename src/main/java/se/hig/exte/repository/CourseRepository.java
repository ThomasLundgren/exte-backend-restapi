package se.hig.exte.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import se.hig.exte.model.Course;
import se.hig.exte.model.Subject;

/**
 * An interface used for database operations on {@link Course} objects.
 * References to this interface are automatically given an implementation by Spring Boot when using
 * the {@code Autowired} annotation.
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

	/**
	 * Fetches the {@link Course} object with the given ID from the database.
	 * @param id The ID of the {@link Course} object to fetch.
	 * @return The {@link Course} object representing the database record with the specified ID.
	 */
	Course findById(int id);

	/**
	 * Fetches all {@link Course} objects which belong to the specified {@link Subject} from the database.
	 * @param subjectId The ID of the {@link Subject}.
	 * @return A {@code List} containing all {@link Course}s belonging to the {@link Subject} with the specified ID.
	 */
	List<Course> findBySubjectId(int subjectId);
	
	@Query("FROM Course g where g.name = :name")
	List<Course> findAllByName(@Param("name") String text);
}