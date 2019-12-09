package se.hig.exte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.hig.exte.model.Course;
import se.hig.exte.repository.CourseRepository;

@Service
public class CourseService implements IService<Course> {

	private final CourseRepository courseRepo;

	@Autowired
	public CourseService(CourseRepository courseRepo) {
		this.courseRepo = courseRepo;
	}

	@Override
	public Course save(Course course) {
		System.out.println(course);
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
	
	public List<Course> findBySubjectId(int subjectId) {
		return courseRepo.findBySubjectId(subjectId);
	}

	@Override
	public List<Course> findAll() {
		return courseRepo.findAll();
	}
	
	
}
