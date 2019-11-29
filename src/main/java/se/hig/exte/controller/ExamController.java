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

import se.hig.exte.model.Exam;
import se.hig.exte.repository.ExamRepository;
import se.hig.exte.service.AddExamService;

@RestController
@RequestMapping("/api/exam")
public class ExamController {
	
	private final AddExamService addExamService;
	private final ExamRepository examRepository;

	@Autowired
	public ExamController(AddExamService addExamService, ExamRepository examRepository) {
		this.addExamService = addExamService;
		this.examRepository = examRepository;
	}
	
//	@RequestMapping("/index")
//    public ResponseEntity<String> index() {
//		return ResponseEntity.status(HttpStatus.OK).body("Welcome!");
//    }
	
	@PostMapping("/add")
	public ResponseEntity<Exam> create(@RequestBody Exam exam) {
		Exam savedExam = examRepository.save(exam);
		return new ResponseEntity<Exam>(savedExam, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public Exam getExam(@PathVariable String id) {
		int examId = Integer.parseInt(id);
		return examRepository.findById(examId);
	}

}
