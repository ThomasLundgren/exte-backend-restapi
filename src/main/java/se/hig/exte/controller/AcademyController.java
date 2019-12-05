package se.hig.exte.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	public ResponseEntity<Academy> save(@RequestBody Academy academy) {
		Academy savedAcademy = academyService.save(academy);
		return new ResponseEntity<Academy>(savedAcademy, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Academy> getSubject(@PathVariable String id) {
		int academyId = Integer.parseInt(id);
		return new ResponseEntity<Academy>(academyService.findById(academyId), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Academy> deleteAcademyById(@PathVariable String id) {
		int parsed = Integer.parseInt(id);
		academyService.deleteById(parsed);
		Academy academy = academyService.findById(parsed);
		ResponseEntity<Academy> responseEntity;
		if (academy == null) {
			responseEntity = new ResponseEntity<Academy>(HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<Academy>(academy, HttpStatus.NO_CONTENT);
		}
		return responseEntity;
	}

}
