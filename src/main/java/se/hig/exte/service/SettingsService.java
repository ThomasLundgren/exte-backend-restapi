package se.hig.exte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import se.hig.exte.model.Settings;
import se.hig.exte.repository.SettingsRepository;

@Service
public class SettingsService {
	
	private final SettingsRepository settingsRepo;
	
	@Autowired
	public SettingsService(SettingsRepository settingsRepo) {
		this.settingsRepo = settingsRepo;
	}
	
	public Settings findById(int id) {
		return settingsRepo.findById(id);
	}
	
	public Settings save(Settings settings) {
		return settingsRepo.save(settings);
	}
	
	public Settings getCurrentSettings() {
		return settingsRepo.findFirstByOrderByCreatedDesc();
	}
	
	public List<Settings> findAllSettingsSorted() {
		return settingsRepo.findAllByOrderByCreatedDesc();
	}
	
	public boolean exists(Settings settings) {
		if (settingsRepo.existsById(settings.getId())) {
			return true;
		} else {
			return false;
		}
	}
}
