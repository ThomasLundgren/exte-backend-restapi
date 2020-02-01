package se.hig.exte.service;

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
	 */
	public Settings findById(int id) {
		return settingsRepo.findById(id);
	}

	/**
	 * Saves a {@link Settings} object to the database.
	 * 
	 * @param settings The {@link Settings} object to save.
	 */
	public Settings save(Settings settings) {
		return settingsRepo.saveAndFlush(settings);
	}

	/**
	 * Fetches the currently active {@link Settings} from the database.
	 */
	public Settings getCurrentSettings() {
		return settingsRepo.findFirstByOrderByCreatedDesc();
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
		return settingsRepo.findTop10ByOrderByCreatedDesc();
	}

	/**
	 * Fetches all {@link Settings} objects from the database and returns them
	 * sorted by creation date with the most recently added {@link Settings} first.
	 * 
	 * @return A {@code List} containing all {@link Settings} objects sorted by
	 *         creation date with the most recently added {@link Settings} first.
	 */
	public List<Settings> findAllSettingsSorted() {
		return settingsRepo.findAllByOrderByCreatedDesc();
	}
	
	/**
	 * Fetches the current HTML code for the "about" web page.
	 * @return the "about" web page HTML as a String.
	 */
	public String findCurrentAboutPageHtml() {
		return settingsRepo.findFirstByOrderByCreatedDesc().getAboutPageHtml();
	}
	
	/**
	 * Fetches the current HTML code for the "home" web page.
	 * @return the "home" web page HTML as a String.
	 */
	public String findCurrentHomePageHtml() {
		return settingsRepo.findFirstByOrderByCreatedDesc().getHomePageHtml();
	}

	/**
	 * Fetches the current HTML code for the "home" web page.
	 * @return the "home" web page HTML as a String.
	 */
	public Integer findCurrentUnpublishTime() {
		return settingsRepo.findFirstByOrderByCreatedDesc().getUnpublishTimeYears();
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
