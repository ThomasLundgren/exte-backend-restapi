package se.hig.exte.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exam")
public class ExamController {
	
//	private final AddExamService addExamService;
//	private final ExamRepository examRepository;
//
//	@Autowired
//	public ExamController(AddExamService addExamService, ExamRepository examRepository) {
//		this.addExamService = addExamService;
//		this.examRepository = examRepository;
//	}
	
//	@RequestMapping("/index")
//    public ResponseEntity<String> index() {
//		return ResponseEntity.status(HttpStatus.OK).body("Welcome!");
//    }
	
//	@PostMapping("/add")
//	public ResponseEntity<Exam> create(@RequestBody Exam exam) {
//		Exam savedExam = examRepository.save(exam);
//		return new ResponseEntity<Exam>(savedExam, HttpStatus.OK);
//	}
//	
//	@GetMapping("/{id}")
//	public Exam getExam(@PathVariable String id) {
//		int examId = Integer.parseInt(id);
//		return examRepository.findById(examId);
//	}

}
