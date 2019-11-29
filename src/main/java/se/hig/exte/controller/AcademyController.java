package se.hig.exte.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.hig.exte.model.Academy;
import se.hig.exte.repository.AcademyRepository;

@RestController
@RequestMapping("/api/academy")
public class AcademyController {

	private final AcademyRepository academyRepository;
	
	public AcademyController(AcademyRepository academyRepository) {
		this.academyRepository = academyRepository;
	}
	
	@PostMapping("/add")
	public ResponseEntity<Academy> create(@RequestBody Academy academy) {
		Academy savedAcademy = academyRepository.save(academy);
		return new ResponseEntity<Academy>(savedAcademy, HttpStatus.OK);
	}
	
}
