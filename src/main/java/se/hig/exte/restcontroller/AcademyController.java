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

@RestController
@RequestMapping("/academies")
public class AcademyController {

	private final AcademyService academyService;

	public AcademyController(AcademyService academyService) {
		this.academyService = academyService;
	}

	@PostMapping("/")
	public ResponseEntity<Academy> saveAcademy(@RequestBody Academy academy) {
		Academy savedAcademy = academyService.save(academy);
		return new ResponseEntity<Academy>(savedAcademy, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Academy>> getAcademy(@PathVariable int id) {
		return new ResponseEntity<List<Academy>>(academyService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Academy> getAllAcademies(@PathVariable int id) {
		return new ResponseEntity<Academy>(academyService.findById(id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public void deleteAcademyById(@PathVariable int id) {
		academyService.deleteById(id);
	}
	
	@PatchMapping("/")
	public ResponseEntity<Academy> updateAcademy(@RequestBody Academy academy) {
		Academy savedAcademy = academyService.save(academy);
		return new ResponseEntity<Academy>(savedAcademy, HttpStatus.OK);
	} 

}
