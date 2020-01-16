package se.hig.exte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import se.hig.exte.model.Settings;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, Integer> {

	Settings findById(int id);

//	@Query("SELECT s FROM Settings s WHERE s.Created = (SELECT MAX(Created) FROM Settings)")
//	@Query(value = "SELECT s FROM Settings s WHERE s.Created = (SELECT MAX(Created) FROM Settings)")
//	Settings findNewestSettings();

	Settings findFirstByOrderByCreatedDesc();

//	@Query("SELECT * FROM Settings WHERE Created = (SELECT MAX(Created) FROM Settings)")
//	void deleteNewestSettings();

	List<Settings> findAllByOrderByCreatedDesc();
}
