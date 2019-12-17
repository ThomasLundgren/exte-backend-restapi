package se.hig.exte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.hig.exte.model.Course;
import se.hig.exte.repository.CourseRepository;

/**
 * A Service used for performing CRUD operations on {@link Course} objects.
 */
@Service
public class CourseService implements CrudService<Course> {

	private final CourseRepository courseRepo;

	/**
	 * Creates a {@code CourseService} object.
	 * @param courseRepo The {@link CourseRepository} to use for CRUD operations.
	 */
	@Autowired
	public CourseService(CourseRepository courseRepo) {
		this.courseRepo = courseRepo;
	}

	/**
	 * Saves a {@link Course} object to the database.
	 * @param course The {@link Course} object to save.
	 */
	@Override
	public Course save(Course course) {
		return courseRepo.save(course);
	}

	/**
	 * Fetches the {@link Course} object with the corresponding ID from the database.
	 * @param id The ID of the {@link Course} object to fetch.
	 */
	@Override
	public Course findById(int id) {
		return courseRepo.findById(id);
	}
	
	/**
	 * Fetches all {@link Course} objects from the database.
	 */
	@Override
	public List<Course> findAll() {
		return courseRepo.findAll();
	}
	
	/**
	 * Fetches all {@link Course} objects which belong to the {@link Subject} with the specified ID.
	 * @param subjectId The ID of the {@link Subject} which {@link Course}s to fetch.
	 * @return A {@link List} containing all {@link Course}s found.
	 */
	public List<Course> findAllBySubjectId(int subjectId) {
		return courseRepo.findBySubjectId(subjectId);
	}

	/**
	 * Deletes the {@link Course} object with the corresponding ID from the database.
	 * @param id The ID of the {@link Course} object to delete.
	 */
	@Override
	public void deleteById(int id) {
		courseRepo.deleteById(id);
	}
	
	/**
	 * Fetches all {@link Course} that are close-by to the text-
	 * @param text The text searched
	 * @return A {@link List} containing all {@link Course}s found.
	 */
	public List<Course> search(String text) {	
		return courseRepo.findByNameContainingOrCourseCodeContaining(text, text);		
	}
	
	/**
	 * Fetches a list of {@link Course} objects that are unpublished.
	 * @return A {@link List} containing all {@link Course}s found.
	 */
	public List<Course> findAllUnpublished() {
		return courseRepo.findByUnpublishedTrue();
	}
	
	/**
	 * Fetches a list of {@link Course} objects that are published.
	 * @return A {@link List} containing all {@link Course}s found.
	 */
	public List<Course> findAllPublished() {
		return courseRepo.findByUnpublishedFalse();
	}
}
