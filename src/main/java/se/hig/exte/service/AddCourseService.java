package se.hig.exte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.hig.exte.model.Course;
import se.hig.exte.repository.CourseRepository;

@Service
public class AddCourseService {

	private final CourseRepository courseRepository;

	@Autowired
	public AddCourseService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	public void execute(Course course) {
		courseRepository.save(course);
	}

}
