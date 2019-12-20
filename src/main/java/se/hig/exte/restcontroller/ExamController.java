package se.hig.exte.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import se.hig.exte.model.Course;
import se.hig.exte.model.Exam;
import se.hig.exte.service.CookieHandler;
import se.hig.exte.service.CrudService;
import se.hig.exte.service.ExamService;
import se.hig.exte.service.UnpublishService;

/**
 * This class is a RestController class responsible for mapping HTTP requests
 * for the /exams path. It contains mappings of end-points to
 * {@link CrudService}s that operate on {@link Exam} records in the database.
 */
@RestController
@RequestMapping("/exams")
public class ExamController {

	private final ExamService examService;
	private final UnpublishService unpublishService;
	
	/**
	 * Creates an {@code ExamController} object.
	 * 
	 * @param examService The {@link CrudService} class used to perform all services
	 *                    exposed in this RestController.
	 */
	@Autowired
	public ExamController(ExamService addExamService, UnpublishService unpublishService) {
		this.examService = addExamService;
		this.unpublishService = unpublishService;
	}

	/**
	 * Creates an {@link Exam} and stores it in the database.
	 * 
	 * @param exam The {@link Exam} to add in the form of a JSON-object in the POST
	 *             request.
	 * @return A {@code ResponseEntity} object containing the saved {@link Exam} and
	 *         an HTTP status code.
	 */
	@PostMapping("/")
	public ResponseEntity<Exam> saveExam(@RequestBody Exam exam, HttpServletRequest request) {
		if(CookieHandler.isValidAdminSession(request.getCookies())) {
			Exam savedExam = examService.save(exam);
			return new ResponseEntity<Exam>(savedExam, HttpStatus.OK);
		}else {
			return new ResponseEntity<Exam>(HttpStatus.UNAUTHORIZED);
		}
	}

	/**
	 * Fetches the {@link Exam} object with the given ID from the database.
	 * 
	 * @param id The ID of the {@link Exam} to fetch.
	 * @return The {@link Exam} with the given ID.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Exam> getExam(@PathVariable int id) {
		return new ResponseEntity<Exam>(examService.findById(id), HttpStatus.OK);
	}

	/**
	 * Fetches all {@link Exam} records from the database and returns them as a
	 * {@code ResponseEntity} object. List of {@link Exam} objects is automatically
	 * converted to JSON using Spring Boot's {@code HttpMessageConverter} and put in
	 * the {@code ResponseEntity}'s body.
	 * 
	 * @return A {@code ResponseEntity} object containing the fetched {@link Exam}
	 *         objects.
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Exam>> getAllExams() {
		return new ResponseEntity<List<Exam>>(examService.findAll(), HttpStatus.OK);
	}

	/**
	 * Fetches all {@link Exam} objects belonging to the specified {@link Course}
	 * ID. List of {@link Exam} objects is automatically converted to JSON using
	 * Spring Boot's {@code HttpMessageConverter} and put in the
	 * {@code ResponseEntity}'s body.
	 * 
	 * @param id The ID of the {@link Course} to which the {@link Exam}s belongs.
	 * @return A {@code ResponseEntity} object containing a {@code List} of
	 *         {@link Exam} objects.
	 */
	@GetMapping("/course/{id}")
	public ResponseEntity<List<Exam>> getExamByCourseId(@PathVariable int id) {
		List<Exam> exams = examService.findAllByCourseId(id);
		return new ResponseEntity<List<Exam>>(exams, HttpStatus.OK);
	}

	/**
	 * Updates the {@link Exam} object with the given ID in the database.
	 * 
	 * @param exam The {@link Exam} to update in the form of a JSON-object in the
	 *               POST request.
	 * @return A {@code ResponseEntity} object containing the updated {@link Exam}
	 *         and an HTTP status code.
	 */
	@PatchMapping("/")
	public ResponseEntity<Exam> patchExam(@RequestBody Exam exam, HttpServletRequest request) {
		if(CookieHandler.isValidAdminSession(request.getCookies())) {
			Exam patchedExam = examService.save(exam);
			return new ResponseEntity<Exam>(patchedExam, HttpStatus.OK);
		}else {
			return new ResponseEntity<Exam>(HttpStatus.UNAUTHORIZED);
		}
	}

	/**
	 * Deletes the {@link Exam} object with the given ID from the database.
	 * 
	 * @param id The ID of the {@link Exam} to delete.
	 */
	@DeleteMapping("/{id}")
	public void deleteExamById(@PathVariable int id, HttpServletRequest request) {
		if(CookieHandler.isValidSuperSession(request.getCookies()))
			examService.deleteById(id);
	}

	/**
	 * Fetches all unpublished exams.
	 * @return A list of all unpublished exams and the http status OK.
	 */
	@GetMapping("/unpublished")
	public ResponseEntity<List<Exam>> getUnpublishedExams(HttpServletRequest request) {
		if(CookieHandler.isValidAdminSession(request.getCookies()))
			return new ResponseEntity<List<Exam>>(examService.findAllUnpublished(), HttpStatus.OK);
		else
			return new ResponseEntity<List<Exam>>(HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Fetches all published exams.
	 * @return A list of all published exams and the http status OK.
	 */
	@GetMapping("/published")
	public ResponseEntity<List<Exam>> getPublishedExams() {
		return new ResponseEntity<List<Exam>>(examService.findAllPublished(), HttpStatus.OK);
	}	

	/**
	 * Changes the boolean unpublished value on the {@link Exam} 
	 * @param exam The {@link Exam} to update 
	 * @param unpublished The boolean is unpublished
	 * @return The ResponseEntity string of the http status.
	 */
	@PostMapping("/unpublish/{unpublished}")
	public ResponseEntity<String> isExamUnpublished(@RequestBody Exam exam, @PathVariable boolean unpublished, HttpServletRequest request) {
		if(CookieHandler.isValidAdminSession(request.getCookies()))
			return unpublishService.isExamUnpublished(exam, unpublished);
		else
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
	}
	
	/**
	 * This method is run automatically by Spring Boot at 03:00 every day.
	 */
	@Scheduled(cron = "0 0 3 * * *")
	@GetMapping("/testAuto")
	public void autoUnpublish() {
		unpublishService.unpublishExpiredExams();
	}

}
