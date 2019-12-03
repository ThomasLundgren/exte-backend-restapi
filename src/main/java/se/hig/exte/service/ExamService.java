package se.hig.exte.service;

import org.springframework.beans.factory.annotation.Autowired;

import se.hig.exte.model.Exam;
import se.hig.exte.repository.ExamRepository;

public class ExamService implements IService<Exam> {

	private final ExamRepository examRepo;
	
	
	
	/**
	 * @param examRepo
	 */
	@Autowired
	public ExamService(ExamRepository examRepo) {
		this.examRepo = examRepo;
	}

	@Override
	public Exam add(Exam exam) {
		return examRepo.save(exam);
	}

	@Override
	public Exam findById(int id) {
		return examRepo.findById(id);
	}

	@Override
	public void deleteById(int id) {
		examRepo.deleteById(id);
	}
}
