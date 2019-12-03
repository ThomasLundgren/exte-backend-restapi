package se.hig.exte.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.hig.exte.model.Course;
import se.hig.exte.repository.CourseRepository;
import se.hig.exte.service.CourseService;

@RestController
@RequestMapping("/api/course")
public class CourseController {

	private final CourseService courseService;
	
	@Autowired
	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}
	
//	public CourseController() {
//		this.courseService = new CourseService(null);
//	}
	
//	@PostMapping("/add")
//	public ResponseEntity<Exam> create(@RequestBody Exam exam) {
//		Exam savedExam = examRepository.save(exam);
//		return new ResponseEntity<Exam>(savedExam, HttpStatus.OK);
//	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Course> getCourse(@PathVariable String id) {
		int courseId = Integer.parseInt(id);
		return new ResponseEntity<Course>(courseService.findById(courseId), HttpStatus.OK);
	}
	
	@GetMapping("/subject/{id}")
	public ResponseEntity<List<Course>> getCoursesBySubjectId(@PathVariable String id) {
		List<Course> courses = courseService.findBySubjectId(Integer.parseInt(id));
		return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
	}

}
