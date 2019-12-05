package se.hig.exte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.hig.exte.model.Course;
import se.hig.exte.model.Exam;
import se.hig.exte.repository.ExamRepository;

/**
 * A Service used for performing CRUD operations on {@link Exam} objects.
 */
@Service
public class ExamService implements IService<Exam> {

	private final ExamRepository examRepo;
	
	/**
	 * Creates a {@code ExamService} object.
	 * @param examRepo The {@link ExamRepository} to use for CRUD operations.
	 */
	@Autowired
	public ExamService(ExamRepository examRepo) {
		this.examRepo = examRepo;
	}

	/**
	 * Saves a {@link Exam} object to the database.
	 * @param exam The {@link Exam} object to save.
	 */
	@Override
	public Exam save(Exam exam) {
		return examRepo.save(exam);
	}

	/**
	 * Fetches the {@link Exam} object with the corresponding ID from the database.
	 * @param id The ID of the {@link Exam} object to fetch.
	 */
	@Override
	public Exam findById(int id) {
		return examRepo.findById(id);
	}
	
	/**
	 * Fetches all {@link Exam} objects from the database.
	 */
	@Override
	public List<Exam> findAll() {
		return examRepo.findAll();
	}
	
	/**
	 * Fetches all {@link Exam} objects which belong to the {@link Course} with the specified ID.
	 * @param courseId The ID of the {@link Course} which {@link Exam}s to fetch.
	 * @return A {@link List} containing all {@link Exam}s found.
	 */
	public List<Exam> findAllByCourseId(int courseId) {
		return examRepo.findByCourseId(courseId);
	}

	/**
	 * Deletes the {@link Exam} object with the corresponding ID from the database.
	 * @param id The ID of the {@link Exam} object to delete.
	 */
	@Override
	public void deleteById(int id) {
		examRepo.deleteById(id);
	}
	
}
