package se.hig.exte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.hig.exte.model.Exam;
import se.hig.exte.model.User;
import se.hig.exte.repository.ExamRepository;

/**
 * A service that deletes an {@link Exam} object from persistent storage.
 */
@Service
public class RemoveExamService {

	private final ExamRepository examRepo;
	private Exam exam;

	/**
	 * Creates an instance of this service.
	 * @param examRepository The {@link ExamRepository} object used to delete from persistent storage.
	 * @param exam The {@link Exam} object to be removed.
	 */
	@Autowired
	public RemoveExamService(ExamRepository examRepo, Exam exam) {
		this.examRepo = examRepo;
		this.exam = exam;
	}

	/**
	 * Executes this service.
	 * @param exam The {@link Exam} object to remove from persistent storage.
	 */
	public void execute() {
		examRepo.delete(exam);
	}

}
