package se.hig.exte.service;

import java.time.LocalDate;
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
public class ExamService implements CrudService<Exam> {

	private final ExamRepository examRepo;

	/**
	 * Creates a {@code ExamService} object.
	 * 
	 * @param examRepo The {@link ExamRepository} to use for CRUD operations.
	 */
	@Autowired
	public ExamService(ExamRepository examRepo) {
		this.examRepo = examRepo;
	}

	/**
	 * Saves a {@link Exam} object to the database.
	 * 
	 * @param exam The {@link Exam} object to save.
	 */
	@Override
	public Exam save(Exam exam) {
		return examRepo.save(exam);
	}

	/**
	 * Fetches the {@link Exam} object with the corresponding ID from the database.
	 * 
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
	 * Fetches all {@link Exam} objects which belong to the {@link Course} with the
	 * specified ID.
	 * 
	 * @param courseId The ID of the {@link Course} which {@link Exam}s to fetch.
	 * @return A {@link List} containing all {@link Exam}s found.
	 */
	public List<Exam> findAllByCourseId(int courseId) {
		return examRepo.findByCourseId(courseId);
	}

	/**
	 * Deletes the {@link Exam} object with the corresponding ID from the database.
	 * 
	 * @param id The ID of the {@link Exam} object to delete.
	 */
	@Override
	public void deleteById(int id) {
		examRepo.deleteById(id);
	}
	
	/**
	 * Fetches all unpublished {@link Exam}s from the database.
	 * @return A {@link List} containing all unpublished {@link Exam}s.
	 */
	public List<Exam> findAllUnpublished() {
		return examRepo.findByUnpublishedTrue();
	}
	
	/**
	 * Sets the unpublished value of {@link Exam} to true on all exams in the list.
	 */
	public void unpublish() {
		List<Exam> exams = examRepo.findByUnpublishDateLessThanAndUnpublishedFalse(LocalDate.now());
		for (Exam exam : exams) {
			exam.setUnpublished(true);
			examRepo.save(exam);
			System.out.println("Unpublisher unpublished " + exam.toString());
		}
		examRepo.flush();
		System.out.println("Unpublisher finished.");
	}
}
