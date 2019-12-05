package se.hig.exte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.hig.exte.model.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer>{
	
	/*
	 * The Repository interface is the Spring equivalent of DAO classes.
	 */
	
	List<Exam> findByName(String name);
	
	Exam findById(int id);
	
	List<Exam> findByCourseId(int courseId);

}
