package se.hig.exte.restcontroller;

import java.util.List;

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
import se.hig.exte.service.AcademyService;
import se.hig.exte.service.CrudService;

/**
 * This class is a RestController class responsible for mapping HTTP requests for the /academies path.
 * It contains contains mappings of end-points to {@link CrudService}s that operate on {@link Academy} records
 * in the database.
 */
@RestController
@RequestMapping("/academies")
public class AcademyController {

	private final AcademyService academyService;

	/**
	 * Create a {@code AcademyController} object.
	 * @param academyService The {@link CrudService} class used to perform all services exposed in this RestController. 
	 */
	public AcademyController(AcademyService academyService) {
		this.academyService = academyService;
	}

	/**
	 * Creates an {@link Academy} and stores it in the database.
	 * @param academy The {@link Academy} to add in the form of a JSON-object in the POST request.
	 * @return A {@code ResponseEntity} object containing the saved {@link Academy} and an HTTP status code.
	 */
	@PostMapping("/")
	public ResponseEntity<Academy> saveAcademy(@RequestBody Academy academy) {
		Academy savedAcademy = academyService.save(academy);
		return new ResponseEntity<Academy>(savedAcademy, HttpStatus.OK);
	}

	/**
	 * Fetches all {@link Academy} record from the database and returns them as a {@code ResponseEntity}
	 * object. List of {@link Academy} objects is automatically converted to JSON using Spring Boot's 
	 * {@code HttpMessageConverter} and put in the {@code ResponseEntity}'s body.
	 * @return A {@code ResponseEntity} object containing the fetched {@link Academy} objects.
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Academy>> getAllAcademies() {
		return new ResponseEntity<List<Academy>>(academyService.findAll(), HttpStatus.OK);
	}
	
	/**
	 * Fetches the {@link Academy} object with the given ID from the database.
	 * @param id The ID of the {@link Academy} to fetch.
	 * @return The {@link Academy} with the given ID.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Academy> getAcademy(@PathVariable int id) {
		return new ResponseEntity<Academy>(academyService.findById(id), HttpStatus.OK);
	}

	/**
	 * Deletes the {@link Academy} object with the given ID from the database.
	 * @param id The ID of the {@link Academy} to delete.
	 */
	@DeleteMapping("/{id}")
	public void deleteAcademyById(@PathVariable int id) {
		academyService.deleteById(id);
	}
	
	/**
	 * Updates the {@link Academy} object with the given ID in the database.
	 * @param academy The {@link Academy} to update in the form of a JSON-object in the POST request.
	 * @return A {@code ResponseEntity} object containing the updated {@link Academy} and an HTTP status code.
	 */
	@PatchMapping("/")
	public ResponseEntity<Academy> updateAcademy(@RequestBody Academy academy) {
		Academy savedAcademy = academyService.save(academy);
		return new ResponseEntity<Academy>(savedAcademy, HttpStatus.OK);
	} 

}
