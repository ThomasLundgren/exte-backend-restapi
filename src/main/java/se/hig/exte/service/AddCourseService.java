package se.hig.exte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.hig.exte.model.Academy;
import se.hig.exte.model.Course;
import se.hig.exte.repository.AcademyRepository;
import se.hig.exte.repository.CourseRepository;

/**
 * A service that stores an {@link Course} object to persistent storage.
 */
@Service
public class AddCourseService {

	private final CourseRepository courseRepository;
	private Course course;
	/**
	 * Creates an instance of this service.
	 * @param courseRepository The {@link CourseRepository} object used to store to persistent storage.
	 * @param course The {@link Course} object to be stored.
	 */
	@Autowired
	public AddCourseService(CourseRepository courseRepository, Course course) {
		this.courseRepository = courseRepository;
		this.course = course;
	}
	
	/**
	 * Executes this service.
	 * @param course The {@link Course} object to store in persistent storage.
	 */
	public void execute() {
		courseRepository.save(course);
	}

}
