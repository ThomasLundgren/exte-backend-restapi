package se.hig.exte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.hig.exte.model.Exam;
import se.hig.exte.repository.ExamRepository;

/**
 * A service that retrieves an {@link Exam} object from persistent storage.
 */
@Service
public class GetExamByIdService {

	private final ExamRepository examRepo;
	private int id;
	
	/**
	 * Creates an instance of this service.
	 * @param examRepository The {@link ExamRepository} object used to restrive from persistent storage.
	 * @param id The ID of the {@link Exam} to retrieve.
	 */
	@Autowired
	public GetExamByIdService(ExamRepository examRepo, int id) {
		this.examRepo = examRepo;
		this.id = id;
	}
	
	/**
	 * Executes this service.
	 * @return The {@link Exam} object to retrieve from persistent storage.
	 */
	public Exam execute() {
		return examRepo.findById(id);
	}

}
