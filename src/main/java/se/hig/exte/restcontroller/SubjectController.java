package se.hig.exte.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import se.hig.exte.model.Subject;
import se.hig.exte.service.SubjectService;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
	private final SubjectService subjectService;

	@Autowired
	public SubjectController(SubjectService subjectService) {
		this.subjectService = subjectService;
	}

	@PostMapping("/")
	public ResponseEntity<Subject> saveSubject(@RequestBody Subject subject) {
		Subject savedSubject = subjectService.save(subject);
		return new ResponseEntity<Subject>(savedSubject, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Subject> getSubject(@PathVariable int id) {
		return new ResponseEntity<Subject>(subjectService.findById(id), HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Subject>> getAllCourses() {
		return new ResponseEntity<List<Subject>>(subjectService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/academy/{id}")
	public ResponseEntity<List<Subject>> getSubjectByAcademyId(@PathVariable int id) {
		List<Subject> subjects = subjectService.findByAcadmemyId(id);
		return new ResponseEntity<List<Subject>>(subjects, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public void deleteSubjectById(@PathVariable int id) {
		subjectService.deleteById(id);
	}

	@PatchMapping("/")
	public ResponseEntity<Subject> patchSubject(@RequestBody Subject subject) {
		Subject patchedSubject = subjectService.save(subject);
		return new ResponseEntity<Subject>(patchedSubject, HttpStatus.OK);
	}
}
