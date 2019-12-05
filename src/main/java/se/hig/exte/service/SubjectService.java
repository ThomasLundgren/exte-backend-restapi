package se.hig.exte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.hig.exte.model.Subject;
import se.hig.exte.repository.SubjectRepository;

@Service
public class SubjectService implements IService<Subject> {

	private final SubjectRepository subjectRepo;
	
	/**
	 * @param subjectRepo
	 */
	@Autowired
	public SubjectService(SubjectRepository subjectRepo) {
		this.subjectRepo = subjectRepo;
	}

	@Override
	public Subject add(Subject subject) {
		return subjectRepo.save(subject);
	}

	@Override
	public Subject findById(int id) {
		return subjectRepo.findById(id);
	}

	@Override
	public void deleteById(int id) {
		subjectRepo.deleteById(id);
	}

	public List<Subject> findByAcadmemyId(int academyId) {
		return subjectRepo.findByAcademyId(academyId);
	}
	
}
