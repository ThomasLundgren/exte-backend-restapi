package se.hig.exte.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import se.hig.exte.model.Tag;
import se.hig.exte.service.CookieHandler;
import se.hig.exte.service.CrudService;
import se.hig.exte.service.TagService;

/**
 * This class is a RestController class responsible for mapping HTTP requests
 * for the /tags path. It contains mappings of end-points to
 * {@link CrudService}s that operate on {@link Tag} records in the database.
 */
@RestController
@RequestMapping("/tags")
public class TagController {
	private final TagService tagService;
	private final CookieHandler cookieHandler;

	/**
	 * Creates a {@code TagController} object.
	 *
	 * @param tagService   The {@link CrudService} class used to perform all
	 *                         services exposed in this RestController.
	 * @param cookieHandler    object responsible for handling authentication.
	 */
	@Autowired
	public TagController(TagService tagService,	CookieHandler cookieHandler) {
		this.tagService = tagService;
		this.cookieHandler = cookieHandler;
	}

	/**
	 * Creates a {@link Tag} and stores it in the database.
	 *
	 * @param request To see if the user and cookie are valid.
	 * @param tag The {@link Tag} to add in the form of a JSON-object in the
	 *                POST request.
	 * @param request The incoming HTTP request.
	 * @return A {@code ResponseEntity} object containing the saved {@link Tag}
	 *         and an HTTP status code.
	 */
	@PostMapping("/")
	public ResponseEntity<Tag> saveTag(@Valid @RequestBody Tag tag, HttpServletRequest request) {
		if (cookieHandler.isValidSuperSession(request.getCookies())) {
			Tag savedTag = tagService.save(tag);
			return new ResponseEntity<Tag>(savedTag, HttpStatus.OK);
		} else {
			return new ResponseEntity<Tag>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("/test") 
	public ResponseEntity<Tag> testTag(@RequestBody Course course) {
		System.out.println(course);
		
		Tag tag = new Tag("test");
		
		course.getTags().add(tag);
		
		tag.getCourses().add(course);
		
		
		return null;
	}

	/**
	 * Fetches the {@link Tag} object with the given ID from the database.
	 *
	 * @param id The ID of the {@link Tag} to fetch.
	 * @return The {@link Tag} with the given ID.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Tag> getTag(@PathVariable int id) {
		return new ResponseEntity<Tag>(tagService.findById(id), HttpStatus.OK);
	}

	/**
	 * Fetches all {@link Tag} records from the database and returns them as a
	 * {@code ResponseEntity} object. List of {@link Tag} objects is
	 * automatically converted to JSON using Spring Boot's
	 * {@code HttpMessageConverter} and put in the {@code ResponseEntity}'s body.
	 *
	 * @return A {@code ResponseEntity} object containing the fetched
	 *         {@link Tag} objects.
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Tag>> getAllTags() {
		return new ResponseEntity<List<Tag>>(tagService.findAll(), HttpStatus.OK);
	}

	/**
	 * Deletes the {@link Tag} object with the given ID from the database.
	 *
	 * @param id      The ID of the {@link Tag} to delete.
	 * @param request the incoming HTTP request.
	 */
	@DeleteMapping("/{id}")
	public void deleteTagById(@PathVariable int id, HttpServletRequest request) {
		if (cookieHandler.isValidSuperSession(request.getCookies()))
			tagService.deleteById(id);
	}

	@DeleteMapping("/")
	public void deleteTags(@RequestBody List<Tag> tags, HttpServletRequest request) {
		if (cookieHandler.isValidSuperSession(request.getCookies()))
			tagService.deleteAll(tags);
	}
}
