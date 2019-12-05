package se.hig.exte.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {
//	private final SubjectRepository subjectRepository;
//
//	@Autowired
//	public SubjectController(SubjectRepository subjectRepository) {
//		this.subjectRepository = subjectRepository;
//	}
//	
//	
//	@PostMapping("/add")
//	public ResponseEntity<Subject> create(@RequestBody Subject subject) {
//		Subject savedSubject = subjectRepository.save(subject);
//		return new ResponseEntity<Subject>(savedSubject, HttpStatus.OK);
//	}
//	
//	@GetMapping("/{id}")
//	public Subject getSubject(@PathVariable String id) {
//		int subjectId = Integer.parseInt(id);
//		return subjectRepository.findById(subjectId);
//	}
}
