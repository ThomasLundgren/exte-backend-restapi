package se.hig.exte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.hig.exte.model.Exam;
import se.hig.exte.repository.ExamRepository;

@Service
public class RemoveExamService {

	private final ExamRepository examRepo;

	@Autowired
	public RemoveExamService(ExamRepository examRepo) {
		this.examRepo = examRepo;
	}

	public void execute(Exam exam) {
		examRepo.delete(exam);
	}

}
