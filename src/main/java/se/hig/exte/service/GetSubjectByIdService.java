package se.hig.exte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.hig.exte.model.Subject;
import se.hig.exte.repository.SubjectRepository;

/**
 * A service that retrieves a {@link Subject} object from persistent storage.
 */
@Service
public class GetSubjectByIdService {

	private final SubjectRepository subjectRepository;
	private int id;

	/**
	 * Creates an instance of this service.
	 * @param subjectRepository The {@link SubjectRepository} object used to retrieve from persistent storage.
	 * @param id The {@link Subject} object to be stored.
	 */
	@Autowired
	public GetSubjectByIdService(SubjectRepository subjectRepository, int id) {
		this.subjectRepository = subjectRepository;
		this.id = id;
	}
	
	/**
	 * Executes this service.
	 * @param id The {@link Subject} object to retrieve from persistent storage.
	 */
	public Subject execute() {
		return subjectRepository.findById(id);
	}

}
