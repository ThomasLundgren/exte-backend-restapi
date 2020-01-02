package se.hig.exte.service;

import java.util.List;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.hig.exte.model.Academy;
import se.hig.exte.model.Course;
import se.hig.exte.repository.AcademyRepository;

/**
 * A Service used for performing CRUD operations on {@link Academy} objects.
 */
@Service
public class AcademyService implements CrudService<Academy> {

	private final AcademyRepository academyRepo;

	/**
	 * Creates an {@code AcademyService} object.
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

	public void deleteById(int id){
		academyRepo.deleteById(id);
	}

}
