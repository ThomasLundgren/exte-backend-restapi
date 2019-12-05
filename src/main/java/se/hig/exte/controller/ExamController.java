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

import se.hig.exte.model.Exam;
import se.hig.exte.service.ExamService;

@RestController
@RequestMapping("/api/exam")
public class ExamController {
	
	private final ExamService examService;

	@Autowired
	public ExamController(ExamService addExamService) {
		this.examService = addExamService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<Exam> create(@RequestBody Exam exam) {
		Exam savedExam = examService.save(exam);
		return new ResponseEntity<Exam>(savedExam, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Exam> getExam(@PathVariable String id) {
		int examId = Integer.parseInt(id);
		return new ResponseEntity<Exam>(examService.findById(examId), HttpStatus.OK);
	}

	@GetMapping("/course/{id}")
	public ResponseEntity<List<Exam>> getExamByCourseId(@PathVariable String id) {
		List<Exam> exams = examService.findByCourseId(Integer.parseInt(id));
		return new ResponseEntity<List<Exam>>(exams, HttpStatus.OK);
	}
	
}
