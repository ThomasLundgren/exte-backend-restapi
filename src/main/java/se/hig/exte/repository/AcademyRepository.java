package se.hig.exte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.hig.exte.model.Academy;

@Repository
public interface AcademyRepository extends JpaRepository<Academy, Integer> {

	List<Academy> findByName(String name);

	Academy findById(int id);

}
