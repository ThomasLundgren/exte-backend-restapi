package se.hig.exte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import se.hig.exte.model.Course;
import se.hig.exte.repository.CourseRepository;

@Service
public class CourseService implements IFilterableService<Course> {

	private final CourseRepository courseRepo;

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

	@Override
	public List<Course> findAllByParentId(int id) {
		Course course = new Course(null, null, id);
		Example<Course> example = Example.of(course);
		return courseRepo.findAll(example);
	}
	
	
}
