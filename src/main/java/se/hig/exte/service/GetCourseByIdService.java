package se.hig.exte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.hig.exte.model.Course;
import se.hig.exte.model.Exam;
import se.hig.exte.repository.CourseRepository;

/**
 * A service that retrieves a {@link Course} object from persistent storage.
 */
@Service
public class GetCourseByIdService {

	private final CourseRepository courseRepository;
	private int id;
	
	/**
	 * Creates an instance of this service.
	 * @param courseRepository The {@link CourseRepository} object used to retrieves from persistent storage.
	 * @param id The ID of the {@link Course} to retrieve.
	 */
	@Autowired
	public GetCourseByIdService(CourseRepository courseRepository, int id) {
		this.courseRepository = courseRepository;
		this.id = id;
	}
	
	/**
	 * Executes this service.
	 * @return The {@link Course} object to retrieve from persistent storage.
	 */
	public Course execute() {
		return courseRepository.findById(id);
	}

}
