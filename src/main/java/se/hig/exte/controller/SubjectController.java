package se.hig.exte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.hig.exte.model.Subject;
import se.hig.exte.repository.SubjectRepository;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {
	private final SubjectRepository subjectRepository;

	@Autowired
	public SubjectController(SubjectRepository subjectRepository) {
		this.subjectRepository = subjectRepository;
	}
	
	
	@PostMapping("/add")
	public ResponseEntity<Subject> create(@RequestBody Subject subject) {
		Subject savedSubject = subjectRepository.save(subject);
		return new ResponseEntity<Subject>(savedSubject, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public Subject getSubject(@PathVariable String id) {
		int subjectId = Integer.parseInt(id);
		return subjectRepository.findById(subjectId);
	}
}
