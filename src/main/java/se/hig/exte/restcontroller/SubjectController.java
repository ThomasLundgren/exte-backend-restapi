package se.hig.exte.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.hig.exte.model.Academy;
import se.hig.exte.model.Subject;
import se.hig.exte.service.CookieHandler;
import se.hig.exte.service.CrudService;
import se.hig.exte.service.SubjectService;
import se.hig.exte.service.UnpublishService;

/**
 * This class is a RestController class responsible for mapping HTTP requests
 * for the /subjects path. It contains mappings of end-points to
 * {@link CrudService}s that operate on {@link Subject} records in the database.
 */
@RestController
@RequestMapping("/subjects")
public class SubjectController {
	private final SubjectService subjectService;
	private final UnpublishService unpublishService;
	private final CookieHandler cookieHandler;

	/**
	 * Creates a {@code SubjectController} object.
	 *
	 * @param subjectService   The {@link CrudService} class used to perform all
	 *                         services exposed in this RestController.
	 * @param unpublishService A {@link CrudService} class used to perform services
	 *                         exposed in this RestController.
	 * @param cookieHandler    object responsible for handling authentication.
	 */
	@Autowired
	public SubjectController(SubjectService subjectService, UnpublishService unpublishService,
			CookieHandler cookieHandler) {
		this.subjectService = subjectService;
		this.unpublishService = unpublishService;
		this.cookieHandler = cookieHandler;
	}

	/**
	 * Creates a {@link Subject} and stores it in the database.
	 *
	 * @param subject The {@link Subject} to add in the form of a JSON-object in the
	 *                POST request.
	 * @return A {@code ResponseEntity} object containing the saved {@link Subject}
	 *         and an HTTP status code.
	 */
	@PostMapping("/")
	public ResponseEntity<Subject> saveSubject(@Valid @RequestBody Subject subject, HttpServletRequest request) {
		if (cookieHandler.isValidSuperSession(request.getCookies())) {
			Subject savedSubject = subjectService.save(subject);
			return new ResponseEntity<Subject>(savedSubject, HttpStatus.OK);
		} else {
			return new ResponseEntity<Subject>(HttpStatus.UNAUTHORIZED);
		}
	}

	/**
	 * Fetches the {@link Subject} object with the given ID from the database.
	 *
	 * @param id The ID of the {@link Subject} to fetch.
	 * @return The {@link Subject} with the given ID.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Subject> getSubject(@PathVariable int id) {
		return new ResponseEntity<Subject>(subjectService.findById(id), HttpStatus.OK);
	}

	/**
	 * Fetches all {@link Subject} records from the database and returns them as a
	 * {@code ResponseEntity} object. List of {@link Subject} objects is
	 * automatically converted to JSON using Spring Boot's
	 * {@code HttpMessageConverter} and put in the {@code ResponseEntity}'s body.
	 *
	 * @return A {@code ResponseEntity} object containing the fetched
	 *         {@link Subject} objects.
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Subject>> getAllSubjects() {
		return new ResponseEntity<List<Subject>>(subjectService.findAllPublished(), HttpStatus.OK);
	}

	/**
	 * Fetches all {@link Subject} objects belonging to the specified
	 * {@link Academy} ID. List of {@link Subject} objects is automatically
	 * converted to JSON using Spring Boot's {@code HttpMessageConverter} and put in
	 * the {@code ResponseEntity}'s body.
	 *
	 * @param id The ID of the {@link Academy} to which the {@link Subject}s
	 *           belongs.
	 * @return A {@code ResponseEntity} object containing a {@code List} of
	 *         {@link Subject} objects.
	 */
	@GetMapping("/academy/{id}")
	public ResponseEntity<List<Subject>> getUnpublishedSubjectsByAcademyId(@PathVariable int id) {
		List<Subject> subjects = subjectService.findAllUnpublishedByAcadmemyId(id);
		return new ResponseEntity<List<Subject>>(subjects, HttpStatus.OK);
	}

	/**
	 * Fetches all {@link Subject} objects belonging to the specified
	 * {@link Academy} ID. List of {@link Subject} objects is automatically
	 * converted to JSON using Spring Boot's {@code HttpMessageConverter} and put in
	 * the {@code ResponseEntity}'s body.
	 *
	 * @param id The ID of the {@link Academy} to which the {@link Subject}s
	 *           belongs.
	 * @return A {@code ResponseEntity} object containing a {@code List} of
	 *         {@link Subject} objects.
	 */
	@GetMapping("/published/academy/{id}")
	public ResponseEntity<List<Subject>> getPublishedSubjectsByAcademyId(@PathVariable int id) {
		List<Subject> subjects = subjectService.findPublishedByAcadmemyId(id);
		return new ResponseEntity<List<Subject>>(subjects, HttpStatus.OK);
	}

	/**
	 * Updates the {@link Subject} object with the given ID in the database.
	 *
	 * @param subject The {@link Subject} to update in the form of a JSON-object in
	 *                the POST request.
	 * @return A {@code ResponseEntity} object containing the updated
	 *         {@link Subject} and an HTTP status code.
	 */
	@PatchMapping("/")
	public ResponseEntity<Subject> patchSubject(@Valid @RequestBody Subject subject, HttpServletRequest request) {
		if (cookieHandler.isValidSuperSession(request.getCookies())) {
			Subject patchedSubject = subjectService.save(subject);
			return new ResponseEntity<Subject>(patchedSubject, HttpStatus.OK);
		} else {
			return new ResponseEntity<Subject>(HttpStatus.UNAUTHORIZED);
		}
	}

	/**
	 * Deletes the {@link Subject} object with the given ID from the database.
	 *
	 * @param id The ID of the {@link Subject} to delete.
	 */
	@DeleteMapping("/{id}")
	public void deleteSubjectById(@PathVariable int id, HttpServletRequest request) {
		if (cookieHandler.isValidSuperSession(request.getCookies()))
			subjectService.deleteById(id);
	}

	/**
	 * Searches the database after subjects with the text variable
	 *
	 * @param text The text searched
	 * @return A list of all subjects that are a match and the http status OK.
	 */
	@GetMapping("/search/{text}")
	public ResponseEntity<List<Subject>> search(@PathVariable String text, HttpServletRequest request) {
		List<Subject> subjects = subjectService.search(text);
		return new ResponseEntity<List<Subject>>(subjects, HttpStatus.OK);
	}

	/**
	 * Fetches all unpublished subjects.
	 *
	 * @return A list of all unpublished courses and the http status OK.
	 */
	@GetMapping("/unpublished")
	public ResponseEntity<List<Subject>> getUnpublishedSubjects(HttpServletRequest request) {
		if (cookieHandler.isValidSuperSession(request.getCookies())) {
			return new ResponseEntity<List<Subject>>(subjectService.findAllUnpublished(), HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Subject>>(HttpStatus.UNAUTHORIZED);
		}
	}

	/**
	 * Changes the boolean unpublished value on the {@link subject}
	 * 
	 * @param subject     The {@link Subject} to update
	 * @param unpublished The boolean is unpublished
	 * @return The ResponseEntity string of the http status.
	 */
	@PostMapping("/unpublish")
	public ResponseEntity<Subject> unpublishSubject(@Valid @RequestBody Subject subject, HttpServletRequest request) {
		if (cookieHandler.isValidSuperSession(request.getCookies()))
			return new ResponseEntity<Subject>(unpublishService.setSubjectUnpublished(subject), HttpStatus.OK);
		else
			return new ResponseEntity<Subject>(HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Changes the boolean unpublished value on the {@link subject}s
	 * 
	 * @param subject     The {@link Subject}s to update
	 * @param unpublished The boolean is unpublished
	 * @return The ResponseEntity string of the http status.
	 */
	@PostMapping("/unpublishList")
	public ResponseEntity<List<Subject>> unpublishSubjects(@RequestBody List<Subject> subjects,
			HttpServletRequest request) {
		if (cookieHandler.isValidSuperSession(request.getCookies()))
			return new ResponseEntity<List<Subject>>(unpublishService.setSubjectsUnpublished(subjects), HttpStatus.OK);
		else
			return new ResponseEntity<List<Subject>>(HttpStatus.UNAUTHORIZED);
	}

	/**
	 * This method is run automatically by Spring Boot at 03:00 every day.
	 */
	@Scheduled(cron = "0 4 3 * * *")
	@GetMapping("/testAuto")
	public void autoUnpublish() {
		unpublishService.unpublishEmptySubjects();
	}
}
