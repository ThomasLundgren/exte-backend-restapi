package se.hig.exte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import se.hig.exte.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer>{
	
	List<Subject> findByName(String name);
	
	Subject findById(int name);
}
