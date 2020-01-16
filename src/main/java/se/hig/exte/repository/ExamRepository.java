package se.hig.exte.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.hig.exte.model.Course;
import se.hig.exte.model.Exam;

/**
 * An interface used for database operations on {@link Exam} objects.
 * References to this interface are automatically given an implementation by Spring Boot when using
 * the {@code Autowired} annotation.
 */
@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer>{

	/**
	 * Fetches the {@link Exam} object with the given ID from the database.
	 * @param id The ID of the {@link Exam} object to fetch.
	 * @return The {@link Exam} object representing the database record with the specified ID.
	 */
	Exam findById(int id);
	
	/**
	 * Fetches all {@link Exam} objects which belong to the specified {@link Course} from the database.
	 * @param courseId The ID of the {@link Course}.
	 * @return A {@link List} containing all {@link Exam}s belonging to the {@link Course} with the specified ID.
	 */
	List<Exam> findByCourseIdAndUnpublishedFalse(int courseId);
	
	/**
	 * Fetches all {@link Exam} objects which belong to the specified {@link Course} from the database.
	 * @param courseId The ID of the {@link Course}.
	 * @return A {@link List} containing all {@link Exam}s belonging to the {@link Course} with the specified ID.
	 */
	List<Exam> findByCourseId(int courseId);
	
	/**
	 * Fetches all {@link Exam} objects with the unpublished value set to true.
	 * @return A {@link List} containing all {@link Exam}s with the unpublished value set to true.
	 */
	List<Exam> findByUnpublishedTrue();
	
	/**
	 * Fetches all {@link Exam} objects with the unpublished value set to false.
	 * @return A {@link List} containing all {@link Exam}s with the unpublished value set to false.
	 */
	List<Exam> findByUnpublishedFalse();
	
	/**
	 * Fetches all {@link Exam} objects that have an expired unpublish date and haven't already been flagged as unpublished.
	 * @param currentDate The current date.
	 * @return A {@link List} containing all {@link Exam}s that satisfy the query.
	 */
	List<Exam> findByUnpublishDateLessThanAndUnpublishedFalse(LocalDate currentDate);

}
