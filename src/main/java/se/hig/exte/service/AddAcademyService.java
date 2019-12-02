package se.hig.exte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.hig.exte.model.Academy;
import se.hig.exte.repository.AcademyRepository;

@Service
public class AddAcademyService {

	private final AcademyRepository adacemyRepository;

	@Autowired
	public AddAcademyService(AcademyRepository academyRepository) {
		this.adacemyRepository = academyRepository;
	}
	
	public void execute(Academy academy) {
		adacemyRepository.save(academy);
	}

}
