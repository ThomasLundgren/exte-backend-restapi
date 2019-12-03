package se.hig.exte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.hig.exte.model.Academy;
import se.hig.exte.repository.AcademyRepository;

/**
 * A service that stores an {@link Academy} object to persistent storage.
 */
@Service
public class AddAcademyService {

	private final AcademyRepository adacemyRepository;
	private Academy academy;

	/**
	 * Creates an instance of this service.
	 * @param academyRepository The {@link AcademyRepository} object used to store to persistent storage.
	 * @param academy The {@link Academy} object to be stored.
	 */
	@Autowired
	public AddAcademyService(AcademyRepository academyRepository, Academy academy) {
		this.adacemyRepository = academyRepository;
		this.academy = academy;
	}
	
	/**
	 * Executes this service.
	 * @param academy The {@link Academy} object to store in persistent storage.
	 */
	public void execute() {
		adacemyRepository.save(academy);
	}

}
