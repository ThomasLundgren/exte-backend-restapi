package se.hig.exte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.hig.exte.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer>{
	
	Subject findByName(String name);
}
