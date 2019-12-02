package se.hig.exte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.hig.exte.model.Academy;
import se.hig.exte.repository.AcademyRepository;

@Service
public class GetAcademyByIdService {

	@Autowired
	private AcademyRepository academyRepository;
	private int id;
	
	public GetAcademyByIdService(AcademyRepository academyRepository) {
		this.academyRepository = academyRepository;
	}
	
	public GetAcademyByIdService(int id) {
		this.id = id;
	}
	
	public Academy execute() {
		return academyRepository.findById(id);
	}
	
}
