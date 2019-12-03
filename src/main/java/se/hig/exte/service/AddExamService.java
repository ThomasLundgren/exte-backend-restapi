package se.hig.exte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.hig.exte.model.Exam;
import se.hig.exte.repository.ExamRepository;

/**
 * A service that stores an {@link Exam} object to persistent storage.
 */
@Service
public class AddExamService {

	private final ExamRepository examRepo;
	private Exam exam;
	
	/**
	 * Creates an instance of this service.
	 * @param examRepository The {@link ExamRepository} object used to store to persistent storage.
	 * @param exam The {@link Exam} object to be stored.
	 */
	@Autowired
	public AddExamService(ExamRepository examRepo, Exam exam) {
		this.examRepo = examRepo;
		this.exam = exam;
	}
	
	/**
	 * Executes this service.
	 * @param exam The {@link Exam} object to store in persistent storage.
	 */
	public void execute() {
		examRepo.save(exam);
	}

}
