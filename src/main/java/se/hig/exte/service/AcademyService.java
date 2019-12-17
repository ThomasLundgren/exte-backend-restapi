package se.hig.exte.service;

import java.util.List;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.hig.exte.model.Academy;
import se.hig.exte.repository.AcademyRepository;

@Service
public class AcademyService {

	private final AcademyRepository academyRepo;
	
	@Autowired
	public AcademyService(AcademyRepository academyRepo) {
		this.academyRepo = academyRepo;
	}

	//@Override
	public Academy save(Academy academy) {
		return academyRepo.save(academy);
	}

	//@Override
	public Academy findById(int id) {
		return academyRepo.findById(id);
	}
	
	public List<Academy> findAll() {
		return academyRepo.findAll();
	}

	public void deleteById(int id, Cookie[] cookies) throws AuthenticationException {
		if(CookieHandler.isValidSuperSession(cookies))
			academyRepo.deleteById(id);
		else 
			throw new AuthenticationException();
	}

}
