package se.hig.exte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.hig.exte.model.Course;
import se.hig.exte.repository.CourseRepository;

@RestController
@RequestMapping("/api/course")
public class CourseController {
	private final CourseRepository courseRepository;

	@Autowired
	public CourseController(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
//	@PostMapping("/add")
//	public ResponseEntity<Exam> create(@RequestBody Exam exam) {
//		Exam savedExam = examRepository.save(exam);
//		return new ResponseEntity<Exam>(savedExam, HttpStatus.OK);
//	}
	
	@GetMapping("/{id}")
	public Course getCourse(@PathVariable String id) {
		int examId = Integer.parseInt(id);
		return courseRepository.findById(examId);
	}

}
