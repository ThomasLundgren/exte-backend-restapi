package se.hig.exte.controller;

import java.util.List;

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
import se.hig.exte.service.SubjectService;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {
	private final SubjectService subjectService;

	@Autowired
	public SubjectController(SubjectService subjectService) {
		this.subjectService = subjectService;
	}


	@PostMapping("/add")
	public ResponseEntity<Subject> create(@RequestBody Subject subject) {
		Subject savedSubject = subjectService.add(subject);
		return new ResponseEntity<Subject>(savedSubject, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Subject> getSubject(@PathVariable String id) {
		int subjectId = Integer.parseInt(id);
		return new ResponseEntity<Subject>(subjectService.findById(subjectId), HttpStatus.OK);
	}

	@GetMapping("/academy/{id}")
	public ResponseEntity<List<Subject>> getSubjectByAcademyId(@PathVariable String id) {
		List<Subject> subjects = subjectService.findByAcadmemyId(Integer.parseInt(id));
		return new ResponseEntity<List<Subject>>(subjects, HttpStatus.OK);
	}
}
