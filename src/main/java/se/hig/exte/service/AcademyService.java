package se.hig.exte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.hig.exte.model.Academy;
import se.hig.exte.repository.AcademyRepository;

@Service
public class AcademyService implements IService<Academy> {

	private final AcademyRepository academyRepo;
	
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

	@Override
	public void deleteById(int id) {
		academyRepo.deleteById(id);
	}
	
}
