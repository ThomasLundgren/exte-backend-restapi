package se.hig.exte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.hig.exte.model.Academy;
import se.hig.exte.repository.AcademyRepository;

/**
 * A service that retrieves an {@link Academy} object from persistent storage.
 * @author Thomas Lundgren, Sanna Lundqvist, Mattias Melchior, Hanna Meden, Niklas Nordgren
 */
@Service
public class GetAcademyByIdService {

	@Autowired
	private AcademyRepository academyRepository;
	private int id;
	
	/**
	 * Creates an instance of this service.
	 * @param academyRepository The {@link AcademyRepository} object used to fetch from persistent storage.
	 * @param id The ID of the {@link Academy} to fetch.
	 */
	public GetAcademyByIdService(AcademyRepository academyRepository, int id) {
		this.academyRepository = academyRepository;
		this.id = id;
	}
	
	/**
	 * Executes this service.
	 * @return The {@link Academy} object to fetch from persistent storage.
	 */
	public Academy execute() {
		return academyRepository.findById(id);
	}
	
}
