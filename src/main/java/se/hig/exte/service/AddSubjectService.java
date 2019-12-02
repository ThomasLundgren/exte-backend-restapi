package se.hig.exte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.hig.exte.model.Subject;
import se.hig.exte.repository.SubjectRepository;

@Service
public class AddSubjectService {

	private final SubjectRepository subjectRepository;

	@Autowired
	public AddSubjectService(SubjectRepository subjectRepository) {
		this.subjectRepository = subjectRepository;
	}
	
	public void execute(Subject subject) {
		subjectRepository.save(subject);
	}

}
