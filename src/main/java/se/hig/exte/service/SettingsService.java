package se.hig.exte.service;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public Settings getSettings() {
		return settingsRepo.findById(1);
	}
	
	public Settings update(Settings settings) {
		return settingsRepo.save(settings);
	}
	
}
