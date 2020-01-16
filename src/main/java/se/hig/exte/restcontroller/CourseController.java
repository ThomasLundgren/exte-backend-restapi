package se.hig.exte.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import se.hig.exte.model.Subject;
import se.hig.exte.service.CookieHandler;
import se.hig.exte.service.CourseService;
import se.hig.exte.service.CrudService;
import se.hig.exte.service.UnpublishService;

/**
 * This class is a RestController class responsible for mapping HTTP requests
 * for the /courses path. It contains mappings of end-points to
 * {@link CrudService}s that operate on {@link Course} records in the database.
 */
@RestController
@RequestMapping("/courses")
public class CourseController {

	private final CourseService courseService;
	private final UnpublishService unpublishService;
	private final CookieHandler cookieHandler;

	/**
	 * Creates a {@code CourseController} object.
	 * 
	 * @param courseService The {@link CrudService} class used to perform all
	 *                      services exposed in this RestController.
	 */
	@Autowired
	public CourseController(CourseService courseService, UnpublishService unpublishService,
			CookieHandler cookieHandler) {
		this.courseService = courseService;
		this.unpublishService = unpublishService;
		this.cookieHandler = cookieHandler;
	}

	/**
	 * Creates a {@link Course} and stores it in the database.
	 * 
	 * @param course The {@link Course} to add in the form of a JSON-object in the
	 *               POST request.
	 * @return A {@code ResponseEntity} object containing the saved {@link Course}
	 *         and an HTTP status code.
	 */
	@PostMapping("/")
	public ResponseEntity<Course> saveCourse(@RequestBody Course course, HttpServletRequest request) {
		if (cookieHandler.isValidSuperSession(request.getCookies())) {
			Course savedCourse = courseService.save(course);
			return new ResponseEntity<Course>(savedCourse, HttpStatus.OK);
		} else {
			return new ResponseEntity<Course>(HttpStatus.UNAUTHORIZED);
		}
	}

	/**
	 * Fetches the {@link Course} object with the given ID from the database.
	 * 
	 * @param id The ID of the {@link Course} to fetch.
	 * @return The {@link Course} with the given ID.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable int id) {
		return new ResponseEntity<Course>(courseService.findById(id), HttpStatus.OK);
	}

	/**
	 * Fetches all the published {@link Course} records from the database and
	 * returns them as a {@code ResponseEntity} object. List of {@link Course}
	 * objects is automatically converted to JSON using Spring Boot's
	 * {@code HttpMessageConverter} and put in the {@code ResponseEntity}'s body.
	 * 
	 * @return A {@code ResponseEntity} object containing the fetched {@link Course}
	 *         objects.
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Course>> getAllCourses() {
		return new ResponseEntity<List<Course>>(courseService.findAllPublished(), HttpStatus.OK);
	}

	/**
	 * Fetches all {@link Course} objects belonging to the specified {@link Subject}
	 * ID. List of {@link Course} objects is automatically converted to JSON using
	 * Spring Boot's {@code HttpMessageConverter} and put in the
	 * {@code ResponseEntity}'s body.
	 * 
	 * @param id The ID of the {@link Subject} to which the {@link Course}s belongs.
	 * @return A @{@code ResponseEntity} object containing a {@code List} of
	 *         {@link Course} objects.
	 */
	@GetMapping("/subject/{id}")
	public ResponseEntity<List<Course>> getCourseBySubjectId(@PathVariable int id) {
		List<Course> courses = courseService.findAllBySubjectId(id);
		return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
	}

	/**
	 * Updates the {@link Course} object with the given ID in the database.
	 * 
	 * @param course The {@link Course} to update in the form of a JSON-object in
	 *               the POST request.
	 * @return A {@code ResponseEntity} object containing the updated {@link Course}
	 *         and an HTTP status code.
	 */
	@PatchMapping("/")
	public ResponseEntity<Course> updateCourse(@RequestBody Course course, HttpServletRequest request) {
		if (cookieHandler.isValidSuperSession(request.getCookies())) {
			Course savedCourse = courseService.save(course);
			return new ResponseEntity<Course>(savedCourse, HttpStatus.OK);
		} else {
			return new ResponseEntity<Course>(HttpStatus.UNAUTHORIZED);
		}
	}

	/**
	 * Deletes the {@link Course} object with the given ID from the database.
	 * 
	 * @param id The ID of the {@link Course} to delete.
	 */
	@DeleteMapping("/{id}")
	public void deleteCourseById(@PathVariable int id, HttpServletRequest request) {
		if (cookieHandler.isValidSuperSession(request.getCookies()))
			courseService.deleteById(id);
	}

	/**
	 * Searches the database after courses with the text variable
	 * 
	 * @param text The text searched
	 * @return A list of all courses that are a match and the http status OK.
	 */
	@GetMapping("/search/{text}")
	public ResponseEntity<List<Course>> search(@PathVariable String text) {
		List<Course> courses = courseService.findByNameOrCourseCodeContaining(text);
		return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
	}

	/**
	 * Fetches all unpublished courses.
	 * 
	 * @return A list of all unpublished courses and the http status OK.
	 */
	@GetMapping("/unpublished")
	public ResponseEntity<List<Course>> getUnpublishedCourses() {
		return new ResponseEntity<List<Course>>(courseService.findAllUnpublished(), HttpStatus.OK);
	}

	/**
	 * Changes the boolean unpublished value on the {@link Course}
	 * 
	 * @param course      The {@link Course} to update
	 * @param unpublished The boolean is unpublished
	 * @return The ResponseEntity string of the http status.
	 */
	@PostMapping("/unpublish/{unpublished}")
	public ResponseEntity<String> unpublishCourse(@RequestBody Course course, HttpServletRequest request,
			@PathVariable boolean unpublished) {
		if (cookieHandler.isValidSuperSession(request.getCookies()))
			return unpublishService.isCourseUnpublished(course, unpublished);
		else
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
	}

}
