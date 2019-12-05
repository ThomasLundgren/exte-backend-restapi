package se.hig.exte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.hig.exte.model.Exam;
import se.hig.exte.repository.ExamRepository;

@Service
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
	public Exam save(Exam exam) {
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
	
	public List<Exam> findByCourseId(int courseId) {
		return examRepo.findByCourseId(courseId);
	}
}
