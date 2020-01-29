package se.hig.exte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.hig.exte.model.Academy;
import se.hig.exte.model.Exam;
import se.hig.exte.model.Subject;

/**
 * An interface used for database operations on {@link Exam} objects. References
 * to this interface are automatically given an implementation by Spring Boot
 * when using the {@code Autowired} annotation.
 */
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

	/**
	 * Fetches the {@link Subject} object with the given ID from the database.
	 * 
	 * @param id The ID of the {@link Subject} object to fetch.
	 * @return The {@link Exam} object representing the database record with the
	 *         specified ID.
	 */
	Subject findById(int id);

	/**
	 * Fetches all {@link Subject} objects which belong to the specified
	 * {@link Academy} from the database.
	 * 
	 * @param academyId The ID of the {@link Academy}.
	 * @return A {@link List} containing all {@link Subject}s belonging to the
	 *         {@link Academy} with the specified ID.
	 */
	List<Subject> findByAcademyIdAndUnpublishedFalse(int academyId);

	/**
	 * Fetches all {@link Subject} objects which belong to the specified
	 * {@link Academy} from the database.
	 * 
	 * @param academyId The ID of the {@link Academy}.
	 * @return A {@link List} containing all {@link Subject}s belonging to the
	 *         {@link Academy} with the specified ID.
	 */
	List<Subject> findByAcademyId(int academyId);

	/**
	 * Fetches all {@link Subject} objects with the unpublished value set to true.
	 * 
	 * @return A {@link List} containing all {@link Subject}s with the unpublished
	 *         value set to true.
	 */
	List<Subject> findByUnpublishedTrue();

	/**
	 * Fetches all {@link Subject} objects with the unpublished value set to false.
	 * 
	 * @return A {@link List} containing all {@link Subject}s with the unpublished
	 *         value set to false.
	 */
	List<Subject> findByUnpublishedFalse();
	
	/**
	 * Fetches all published {@link Subject} objects that contains the name or code from the
	 * database.
	 * 
	 * @param name The searched {@link Subject} name
	 * @param code The searched {@link Subject} code
	 * @return A {@link List} containing all {@link Subject} that satisfy the query.
	 */
	List<Subject> findByUnpublishedFalseAndNameContainingOrUnpublishedFalseAndCodeContaining(String name, String code);
}
