package se.hig.exte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.hig.exte.model.Course;
import se.hig.exte.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

	private final CourseService courseService;
	
	@Autowired
	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Course> findCourseById(@PathVariable int id) {
		return new ResponseEntity<Course>(courseService.findById(id), HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<Course> saveCourse(@RequestBody Course course) {
		Course savedCourse = courseService.save(course);
		return new ResponseEntity<Course>(savedCourse, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCourseById(@PathVariable int id) {
		courseService.deleteById(id);
	}
	
	@PatchMapping("/")
	public ResponseEntity<Course> updateCourse(@RequestBody Course course) {
		Course savedCourse = courseService.save(course);
		return new ResponseEntity<Course>(savedCourse, HttpStatus.OK);
	}

}
