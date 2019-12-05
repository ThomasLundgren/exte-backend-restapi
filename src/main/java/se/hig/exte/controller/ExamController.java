package se.hig.exte.controller;

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

import se.hig.exte.model.Exam;
import se.hig.exte.service.ExamService;

@RestController
@RequestMapping("/exams")
public class ExamController {
	
	private final ExamService examService;

	@Autowired
	public ExamController(ExamService addExamService) {
		this.examService = addExamService;
	}
	
	@PostMapping("/")
	public ResponseEntity<Exam> saveExam(@RequestBody Exam exam) {
		Exam savedExam = examService.save(exam);
		return new ResponseEntity<Exam>(savedExam, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Exam> getExam(@PathVariable int id) {
		return new ResponseEntity<Exam>(examService.findById(id), HttpStatus.OK);
	}

	@GetMapping("/course/{id}")
	public ResponseEntity<List<Exam>> getExamByCourseId(@PathVariable int id) {
		List<Exam> exams = examService.findByCourseId(id);
		return new ResponseEntity<List<Exam>>(exams, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public void deleteExamById(@PathVariable int id) {
		examService.deleteById(id);		
	}
	
	@PatchMapping("/")
	public ResponseEntity<Exam> patchExam(@RequestBody Exam exam) {
		Exam patchedExam = examService.save(exam);
		return new ResponseEntity<Exam>(patchedExam, HttpStatus.OK);	
	}
	
}
