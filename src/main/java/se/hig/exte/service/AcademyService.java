package se.hig.exte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.hig.exte.repository.AcademyRepository;

@Service
public class AcademyService {

	private final AcademyRepository academyRepo;
	
	@Autowired
	public AcademyService(AcademyRepository academyRepo) {
		this.academyRepo = academyRepo;
	}
	
	
	
}
