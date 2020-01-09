package se.hig.exte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.hig.exte.model.Settings;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, Integer>{

	Settings findById(int id);
	
}
