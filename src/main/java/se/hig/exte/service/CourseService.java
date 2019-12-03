package se.hig.exte.service;

import org.springframework.beans.factory.annotation.Autowired;

import se.hig.exte.model.Course;
import se.hig.exte.repository.CourseRepository;

public class CourseService implements IService<Course> {

	private final CourseRepository courseRepo;
	
	/**
	 * @param courseRepo
	 */
	@Autowired
	public CourseService(CourseRepository courseRepo) {
		this.courseRepo = courseRepo;
	}

	@Override
	public Course add(Course course) {
		return courseRepo.save(course);
	}

	@Override
	public Course findById(int id) {
		return courseRepo.findById(id);
	}

	@Override
	public void deleteById(int id) {
		courseRepo.deleteById(id);
	}
}
