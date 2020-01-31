package se.hig.exte.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.hig.exte.model.Course;
import se.hig.exte.model.Settings;
import se.hig.exte.repository.SettingsRepository;

/**
 * A Service used for performing CRUD operations on {@link Course} objects.
 */
@Service
public class SettingsService {

	private final SettingsRepository settingsRepo;
	private final Settings defaultSettings = new Settings(120, "<h1>Welcome!</h1>", "<h1>Contact at HiG:s webpage</h1>", 3);

	/**
	 * Creates a {@code SettingsService} object.
	 * 
	 * @param settingsRepo The {@link SettingsRepository} to use for CRUD
	 *                     operations.
	 */
	@Autowired
	public SettingsService(SettingsRepository settingsRepo) {
		this.settingsRepo = settingsRepo;
	}

	/**
	 * Fetches the {@link Settings} object with the corresponding ID from the
	 * database.
	 * 
	 * @param id The ID of the {@link Settings} object to fetch.
	 * @return the settings with the id
	 */
	public Settings findById(int id) {
		return settingsRepo.findById(id);
	}

	/**
	 * Saves a {@link Settings} object to the database.
	 * 
	 * @param settings The {@link Settings} object to save.
	 * @return the setting just saved
	 */
	public Settings save(Settings settings) {
		return settingsRepo.saveAndFlush(settings);
	}

	/**
	 * Fetches the currently active {@link Settings} from the database.
	 * If no settings is available default settings will be applied.
	 * 
	 * @return the currently active Setting in the database
	 */
	public Settings getCurrentSettings() {
		Settings settings = settingsRepo.findFirstByOrderByCreatedDesc();
		if(settings == null) {
			settings = this.defaultSettings;
		}
		return settings;
	}

	/**
	 * Fetches the ten most newly created {@link Settings} objects from the database
	 * and returns them sorted by creation date with the most recently added
	 * {@link Settings} first.
	 * 
	 * @return A {@code List} containing the ten most newly created {@link Settings}
	 *         objects sorted by creation date with the most recently added
	 *         {@link Settings} first.
	 */
	public List<Settings> findTenLatestSettings() {
		List<Settings> settings = settingsRepo.findTop10ByOrderByCreatedDesc();
		if((settings == null) || (settings.size() < 1)) {
			settings = new ArrayList<Settings>();
			settings.add(defaultSettings);
		}
		return settings;
	}

	/**
	 * Fetches all {@link Settings} objects from the database and returns them
	 * sorted by creation date with the most recently added {@link Settings} first.
	 * 
	 * @return A {@code List} containing all {@link Settings} objects sorted by
	 *         creation date with the most recently added {@link Settings} first.
	 */
	public List<Settings> findAllSettingsSorted() {
		List<Settings> settings = settingsRepo.findAllByOrderByCreatedDesc();
		if((settings == null) || (settings.size() < 1)) {
			settings = new ArrayList<Settings>();
			settings.add(defaultSettings);
		}
		return settings;
	}

	/**
	 * Fetches the current HTML code for the "about" web page.
	 * 
	 * @return the "about" web page HTML as a String.
	 */
	public String findCurrentAboutPageHtml() {
		Settings currentSetting = this.getCurrentSettings();
		return currentSetting.getAboutPageHtml();
	}

	/**
	 * Fetches the current HTML code for the "home" web page.
	 * 
	 * @return the "home" web page HTML as a String.
	 */
	public String findCurrentHomePageHtml() {
		Settings currentSetting = this.getCurrentSettings();
		return currentSetting.getHomePageHtml();
	}

	/**
	 * Fetches the current HTML code for the "home" web page.
	 * 
	 * @return the "home" web page HTML as a String.
	 */
	public Integer findCurrentUnpublishTime() {
		Settings currentSetting = this.getCurrentSettings();
		return currentSetting.getUnpublishTimeYears();
	}

	/**
	 * Checks if the specified {@link Settings} object already exists.
	 * 
	 * @param settings The {@link Settings} object which to check.
	 * @return True if the {@link Settings} already exists, otherwise false.
	 */
	public boolean exists(Settings settings) {
		if (settingsRepo.existsById(settings.getId())) {
			return true;
		} else {
			return false;
		}
	}
}
