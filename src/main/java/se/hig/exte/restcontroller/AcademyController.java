package se.hig.exte.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import se.hig.exte.service.AcademyService;
import se.hig.exte.service.CookieHandler;
import se.hig.exte.service.CrudService;
import se.hig.exte.service.UnpublishService;

/**
 * This class is a RestController class responsible for mapping HTTP requests
 * for the /academies path. It contains mappings of end-points to
 * {@link CrudService}s that operate on {@link Academy} records in the database.
 */
@CrossOrigin(origins = "http://localhost:80", maxAge = 3600)
@RestController
@RequestMapping("/academies")
public class AcademyController {

	private final AcademyService academyService;
	private final UnpublishService unpublishService;
	private final CookieHandler cookieHandler;

	/**
	 * Creates an {@code AcademyController} object.
	 *
	 * @param academyService   The {@link CrudService} class used to perform all
	 *                         services exposed in this RestController.
	 * @param unpublishService A {@link CrudService} class used to perform services
	 *                         exposed in this RestController.
	 * @param cookieHandler    object responsible for handling authentication.
	 */
	@Autowired
	public AcademyController(AcademyService academyService, UnpublishService unpublishService,
			CookieHandler cookieHandler) {
		this.academyService = academyService;
		this.unpublishService = unpublishService;
		this.cookieHandler = cookieHandler;
	}

	/**
	 * Gets All published academies
	 *
	 * @return
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Academy>> getAllAcademies() {
		return new ResponseEntity<List<Academy>>(academyService.findAllPublished(), HttpStatus.OK);
	}

	/**
	 * Creates an {@link Academy} and stores it in the database. Only accessible by
	 * super-admin.
	 *
	 * @param academy The {@link Academy} to add in the form of a JSON-object in the
	 *                POST request.
	 * @return A {@code ResponseEntity} object containing the saved {@link Academy}
	 *         and an HTTP status code.
	 */
	@PostMapping("/")
	public ResponseEntity<Academy> saveAcademy(@Valid @RequestBody Academy academy, HttpServletRequest request) {
		if (cookieHandler.isValidSuperSession(request.getCookies())) {
			Academy savedAcademy = academyService.save(academy);
			return new ResponseEntity<Academy>(savedAcademy, HttpStatus.OK);
		} else {
			return new ResponseEntity<Academy>(HttpStatus.UNAUTHORIZED);
		}
	}

	/**
	 * Fetches the {@link Academy} object with the given ID from the database.
	 *
	 * @param id The ID of the {@link Academy} to fetch.
	 * @return The {@link Academy} with the given ID.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Academy> getAcademy(@PathVariable int id) {
		return new ResponseEntity<Academy>(academyService.findById(id), HttpStatus.OK);
	}

	/**
	 * Updates the {@link Academy} object with the given ID in the database. Only
	 * accessible by Super-admin
	 *
	 * @param academy The {@link Academy} to update in the form of a JSON-object in
	 *                the POST request.
	 * @return A {@code ResponseEntity} object containing the updated
	 *         {@link Academy} and an HTTP status code.
	 */
	@PatchMapping("/")
	public ResponseEntity<Academy> updateAcademy(@Valid @RequestBody Academy academy, HttpServletRequest request) {
		if (cookieHandler.isValidSuperSession(request.getCookies())) {
			Academy savedAcademy = academyService.save(academy);
			return new ResponseEntity<Academy>(savedAcademy, HttpStatus.OK);
		} else {
			return new ResponseEntity<Academy>(HttpStatus.UNAUTHORIZED);
		}
	}

	/**
	 * Deletes the {@link Academy} object with the given ID from the database. Only
	 * accesable by super-admin
	 *
	 * @param id The ID of the {@link Academy} to delete.
	 */
	@DeleteMapping("/{id}")
	public boolean deleteAcademyById(@PathVariable int id, HttpServletRequest request) {
		if (cookieHandler.isValidSuperSession(request.getCookies())) {
			academyService.deleteById(id);
			return true;
		}
		return false;
	}

	/**
	 * Changes the boolean unpublished value on the {@link Academy}
	 *
	 * @param academy The {@link Academy} to update
	 * @return The ResponseEntity string of the http status.
	 */
	@PostMapping("/unpublish")
	public ResponseEntity<Academy> unpublishAcademy(@Valid @RequestBody Academy academy, HttpServletRequest request) {
		if (cookieHandler.isValidSuperSession(request.getCookies()))
			return new ResponseEntity<Academy>(unpublishService.setAcademyUnpublished(academy), HttpStatus.OK);
		else
			return new ResponseEntity<Academy>(HttpStatus.UNAUTHORIZED);
	}

	/*
	 * Fetches all unpublished courses.
	 *
	 * @return A list of all unpublished courses and the http status OK.
	 */
	@GetMapping("/unpublished")
	public ResponseEntity<List<Academy>> getUnpublishedAcademies() {
		return new ResponseEntity<List<Academy>>(academyService.findAllUnpublished(), HttpStatus.OK);
	}

	/**
	 * Changes the boolean unpublished value on the {@link subject}s
	 * 
	 * @param subject     The {@link Subject}s to update
	 * @param unpublished The boolean is unpublished
	 * @return The ResponseEntity string of the http status.
	 */
	@PostMapping("/unpublishList")
	public ResponseEntity<List<Academy>> unpublishSubjects(@RequestBody List<Academy> academies,
			HttpServletRequest request) {
		if (cookieHandler.isValidSuperSession(request.getCookies()))
			return new ResponseEntity<List<Academy>>(unpublishService.setAcademiesUnpublished(academies),
					HttpStatus.OK);
		else
			return new ResponseEntity<List<Academy>>(HttpStatus.UNAUTHORIZED);
	}

	/**
	 * This method is run automatically by Spring Boot at 03:00 every day.
	 */
	@Scheduled(cron = "0 6 3 * * *")
	@GetMapping("/testAuto")
	public void autoUnpublish() {
		unpublishService.unpublishEmptyAcademies();
	}
}
