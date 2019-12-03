package se.hig.exte.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.hig.exte.model.Academy;
import se.hig.exte.repository.AcademyRepository;
import se.hig.exte.service.AcademyService;

@RestController
@RequestMapping("/api/academy")
public class AcademyController {

	private final AcademyService academyService;
	
	public AcademyController(AcademyService academyService) {
		this.academyService = academyService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<Academy> add(@RequestBody Academy academy) {
		Academy savedAcademy = academyService.add(academy);
		return new ResponseEntity<Academy>(savedAcademy, HttpStatus.OK);
	}
//
//	@GetMapping("/{id}")
//	public Academy getSubject(@PathVariable String id) {
//		int academyId = Integer.parseInt(id);
//		return academyRepository.findById(academyId);
//	}

}
