package se.hig.exte.restcontroller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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
@RestController
@RequestMapping("/academies")
public class AcademyController {

	private final AcademyService academyService;
	private UnpublishService unpublishService;

	/**
	 * Creates an {@code AcademyController} object.
	 *
	 * @param academyService The {@link CrudService} class used to perform all
	 *                       services exposed in this RestController.
	 */
	public AcademyController(AcademyService academyService, UnpublishService unpublishService) {
		this.academyService = academyService;
		this.unpublishService = unpublishService;
	}

	/**
	 * Gets All published academies
	 * @return
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Academy>> getAllAcademies() {
		return new ResponseEntity<List<Academy>>(academyService.findAllPublished(), HttpStatus.OK);
	}

	private void printAllCookies(HttpServletRequest request) {
		Cookie[] cookiesFromUser = request.getCookies();
		if (cookiesFromUser != null) {
			System.out.println(Arrays.stream(cookiesFromUser).map(c -> c.getName() + "=" + c.getValue())
					.collect(Collectors.joining(", ")));
		}
	}

	/**
	 * Creates an {@link Academy} and stores it in the database. Only accesable by
	 * super-admin
	 * 
	 * @param academy The {@link Academy} to add in the form of a JSON-object in the
	 *                POST request.
	 * @return A {@code ResponseEntity} object containing the saved {@link Academy}
	 *         and an HTTP status code.
	 */
	@RequestMapping("/")
	@PostMapping("/")
	public ResponseEntity<Academy> saveAcademy(@RequestBody Academy academy, HttpServletRequest request) {
		if (CookieHandler.isValidSuperSession(request.getCookies())) {
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
	public ResponseEntity<Academy> updateAcademy(@RequestBody Academy academy, HttpServletRequest request) {
		if (CookieHandler.isValidSuperSession(request.getCookies())) {
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
		if (CookieHandler.isValidSuperSession(request.getCookies())) {
			academyService.deleteById(id);
			return true;
		}
		return false;
	}
	
	/**
	 * Changes the boolean unpublished value on the {@link Academy} 
	 * @param subject The {@link Subject} to update 
	 * @param unpublished The boolean is unpublished
	 * @return The ResponseEntity string of the http status.
	 */
	@PostMapping("/unpublish/{unpublished}")
	public ResponseEntity<String> unpublishAcademy(@RequestBody List<Academy> academies, @PathVariable boolean unpublished, HttpServletRequest request) {
		if(CookieHandler.isValidSuperSession(request.getCookies()))
			return unpublishService.isAcademiesUnpublished(academies, unpublished);	
		else
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
	}
}
