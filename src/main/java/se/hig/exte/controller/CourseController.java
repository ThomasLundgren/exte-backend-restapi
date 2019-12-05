package se.hig.exte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	public ResponseEntity<Course> getCourse(@PathVariable String id) {
		int courseId = Integer.parseInt(id);
		return new ResponseEntity<Course>(courseService.findById(courseId), HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<Course> save(@RequestBody Course course) {
		Course savedCourse = courseService.add(course);
		return new ResponseEntity<Course>(savedCourse, HttpStatus.OK);
	}
	
	@GetMapping("/subject/{id}")
	public ResponseEntity<List<Course>> getCoursesBySubjectId(@PathVariable String id) {
		List<Course> courses = courseService.findBySubjectId(Integer.parseInt(id));
		return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Course> deleteCourseById(@PathVariable String id) {
		int parsed = Integer.parseInt(id);
		courseService.deleteById(parsed);
		Course course = courseService.findById(parsed);
		ResponseEntity<Course> responseEntity;
		if (course == null) {
			responseEntity = new ResponseEntity<Course>(HttpStatus.NO_CONTENT);
		} else {
			responseEntity = new ResponseEntity<Course>(course, HttpStatus.OK);
		}
		return responseEntity;
	}

}
