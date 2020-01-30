package se.hig.exte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.hig.exte.model.Academy;
import se.hig.exte.model.Exam;
import se.hig.exte.repository.AcademyRepository;

/**
 * A Service used for performing CRUD operations on {@link Academy} objects.
 */
@Service
public class AcademyService implements CrudService<Academy> {

	private final AcademyRepository academyRepo;

	/**
	 * Creates an {@code AcademyService} object.
	 * 
	 * @param academyRepo The {@link AcademyRepository} to use for CRUD operations.
	 */
	@Autowired
	public AcademyService(AcademyRepository academyRepo) {
		this.academyRepo = academyRepo;
	}

	@Override
	public Academy save(Academy academy) {
		return academyRepo.save(academy);
	}

	@Override
	public Academy findById(int id) {
		return academyRepo.findById(id);
	}

	/**
	 * Fetches all {@link Academy} objects from the database.
	 */
	public List<Academy> findAll() {
		return academyRepo.findAll();
	}

	public List<Academy> findAllPublished() {
		return academyRepo.findByUnpublishedFalse();
	}

	public void deleteById(int id) {
		academyRepo.deleteById(id);
	}
	
	public void deleteAll(List<Academy> academies) {
		academyRepo.deleteAll(academies);
	}
	

	/**
	 * Fetches a list of {@link Academy} objects that are unpublished.
	 * 
	 * @return A {@link List} containing all {@link Academy}s found.
	 */
	public List<Academy> findAllUnpublished() {
		return academyRepo.findByUnpublishedTrue();
	}
}
