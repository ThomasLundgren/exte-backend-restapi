package se.hig.exte.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.hig.exte.model.Academy;
import se.hig.exte.model.Subject;
import se.hig.exte.repository.SubjectRepository;

/**
 * A Service used for performing CRUD operations on {@link Subject} objects.
 */
@Service
public class SubjectService implements CrudService<Subject> {

	private final SubjectRepository subjectRepo;

	/**
	 * Creates a {@code SibjectService} object.
	 * 
	 * @param subjectRepo The {@link SubjectRepository} to use for CRUD operations.
	 */
	@Autowired
	public SubjectService(SubjectRepository subjectRepo) {
		this.subjectRepo = subjectRepo;
	}

	/**
	 * Saves a {@link Subject} object to the database.
	 * 
	 * @param subject The {@link Subject} object to save.
	 */
	@Override
	public Subject save(Subject subject) {
		return subjectRepo.save(subject);
	}

	/**
	 * Fetches the {@link Subject} object with the corresponding ID from the
	 * database.
	 * 
	 * @param id The ID of the {@link Subject} object to fetch.
	 */
	@Override
	public Subject findById(int id) {
		return subjectRepo.findById(id);
	}

	/**
	 * Fetches all {@link Subject} objects from the database.
	 */
	@Override
	public List<Subject> findAll() {
		return subjectRepo.findAll();
	}

	/**
	 * Fetches all {@link Subject} objects which belong to the {@link Academy} with
	 * the specified ID.
	 * 
	 * @param academyId The ID of the {@link Academy} which {@link Subject}s to
	 *                  fetch.
	 * @return A {@link List} containing all {@link Subject}s found.
	 */
	public List<Subject> findAllUnpublishedByAcadmemyId(int academyId) {
		return subjectRepo.findByAcademyIdAndUnpublishedFalse(academyId);
	}

	public List<Subject> findPublishedByAcadmemyId(int academyId) {
		return subjectRepo.findByAcademyId(academyId).stream().filter(c -> !c.isUnpublished())
				.collect(Collectors.toList());
	}

	/**
	 * Deletes the {@link Subject} object with the corresponding ID from the
	 * database.
	 * 
	 * @param id The ID of the {@link Subject} object to delete.
	 */
	@Override
	public void deleteById(int id) {
		subjectRepo.deleteById(id);
	}

	public void deleteAll(List<Subject> subjects) {
		subjectRepo.deleteAll(subjects);
	}

	/**
	 * Fetches all {@link Subject} that are close-by to the text-
	 * 
	 * @param text The text searched
	 * @return A {@link List} containing all published {@link Subject}s found.
	 */
	public List<Subject> searchPublished(String text) {
		return subjectRepo.findByUnpublishedFalseAndNameContainingOrUnpublishedFalseAndCodeContaining(text, text);
	}

	/**
	 * Fetches a list of {@link Subject} objects that are unpublished.
	 * 
	 * @return A {@link List} containing all {@link Subject}s found.
	 */
	public List<Subject> findAllUnpublished() {
		return subjectRepo.findByUnpublishedTrue();
	}

	/**
	 * Fetches a list of {@link Subject} objects that are published.
	 * 
	 * @return A {@link List} containing all {@link Subject}s found.
	 */
	public List<Subject> findAllPublished() {
		return subjectRepo.findByUnpublishedFalse();
	}
}
