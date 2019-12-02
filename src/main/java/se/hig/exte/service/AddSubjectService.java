package se.hig.exte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.hig.exte.model.Course;
import se.hig.exte.model.Exam;
import se.hig.exte.model.Subject;
import se.hig.exte.repository.CourseRepository;
import se.hig.exte.repository.SubjectRepository;

/**
 * A service that stores an {@link Subject} object to persistent storage.
 */
@Service
public class AddSubjectService {

	private final SubjectRepository subjectRepository;
	private Subject subject;

	/**
	 * Creates an instance of this service.
	 * @param subjectRepository The {@link SubjectRepository} object used to store to persistent storage.
	 * @param subject The {@link Subject} object to be stored.
	 */
	@Autowired
	public AddSubjectService(SubjectRepository subjectRepository, Subject subject) {
		this.subjectRepository = subjectRepository;
		this.subject = subject;
	}
	
	/**
	 * Executes this service.
	 * @param subject The {@link Subject} object to store in persistent storage.
	 */
	public void execute() {
		subjectRepository.save(subject);
	}

}
