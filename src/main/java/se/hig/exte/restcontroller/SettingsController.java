package se.hig.exte.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.hig.exte.model.Settings;
import se.hig.exte.service.CookieHandler;
import se.hig.exte.service.SettingsService;

/**
 * This class is a RestController class responsible for mapping HTTP requests
 * for the /settings path. It contains mappings of end-points to services that
 * operate on {@link Settings} records in the database.
 */
@RestController
@RequestMapping("/settings")
public class SettingsController {

	private final SettingsService settingsService;
	private final CookieHandler cookieHandler;

	/**
	 * Creates a {@code SettingsController} object.
	 *
	 * @param courseService The service class used to perform all services exposed
	 *                      in this RestController.
	 * @param cookieHandler object responsible for handling authentication.
	 */
	@Autowired
	public SettingsController(SettingsService settingsService, CookieHandler cookieHandler) {
		this.settingsService = settingsService;
		this.cookieHandler = cookieHandler;
	}

	/**
	 * Fetches the currently active {@link Settings} from the database.
	 */
	@GetMapping("/")
	public ResponseEntity<Settings> getNewestSettings(HttpServletRequest request) {
		if (cookieHandler.isValidSuperSession(request.getCookies())) {
			Settings settings = settingsService.getCurrentSettings();
			System.out.println(settings.getCreated());
			return new ResponseEntity<Settings>(settings, HttpStatus.OK);
		} else {
			return new ResponseEntity<Settings>(HttpStatus.UNAUTHORIZED);
		}
	}

	/**
	 * Fetches the ten most newly created {@link Settings} objects from the database
	 * and returns them sorted by creation date with the most recently added
	 * {@link Settings} first.
	 * 
	 * @param request The incoming HTTP request.
	 * @return A {@code ReponseEntity} with a {@code List} containing the ten most
	 *         newly created {@link Settings} objects sorted by creation date with
	 *         the most recently added {@link Settings} first, in JSON format.
	 */
	@GetMapping("/tenLatest")
	public ResponseEntity<List<Settings>> getTenLatestSettings(HttpServletRequest request) {
		if (cookieHandler.isValidSuperSession(request.getCookies())) {
			List<Settings> settings = settingsService.findTenLatestSettings();
			return new ResponseEntity<List<Settings>>(settings, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Settings>>(HttpStatus.UNAUTHORIZED);
		}
	}

	/**
	 * Fetches the current HTML code for the "about" web page.
	 * @return the "about" web page HTML as a String.
	 */
	@GetMapping("/about")
	public ResponseEntity<String> getAboutPageHtml(HttpServletRequest request) {
		return new ResponseEntity<String>(settingsService.findCurrentAboutPageHtml(), HttpStatus.OK);
	}
	
	/**
	 * Fetches the current HTML code for the "about" web page.
	 * @return the "about" web page HTML as a String.
	 */
	@GetMapping(value = "/home", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> getHomePageHtml(HttpServletRequest request) {
		String homePageHtml = settingsService.findCurrentHomePageHtml();
		System.out.println(homePageHtml);
		return ResponseEntity.status(HttpStatus.OK).body(settingsService.findCurrentHomePageHtml());
	}

	/**
	 * Fetches all {@link Settings} objects from the database and returns them
	 * sorted by creation date with the most recently added {@link Settings} first.
	 * 
	 * @param request The incoming HTTP request.
	 * @return A @{@code ResponseEntity} with a {@code List} containing all
	 *         {@link Settings} objects sorted by creation date with the most
	 *         recently added {@link Settings} first, in JSON format.
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Settings>> getAllSettings(HttpServletRequest request) {
		if (cookieHandler.isValidSuperSession(request.getCookies())) {
			List<Settings> settings = settingsService.findAllSettingsSorted();
			return new ResponseEntity<List<Settings>>(settings, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Settings>>(HttpStatus.UNAUTHORIZED);
		}
	}

	/**
	 * Saves a {@link Settings} object to the database.
	 * 
	 * @param settings The {@link Settings} object to save.
	 * @param request  The incoming HTTP request.
	 */
	@PostMapping("/")
	public ResponseEntity<Settings> saveSettings(@RequestBody Settings settings, HttpServletRequest request) {
		if (!cookieHandler.isValidSuperSession(request.getCookies())) {
			return new ResponseEntity<Settings>(HttpStatus.UNAUTHORIZED);
		}
		if (settingsService.exists(settings)) {
			return new ResponseEntity<Settings>(HttpStatus.FORBIDDEN);
		}
		Settings newSettings = settingsService.save(settings);
		return new ResponseEntity<Settings>(newSettings, HttpStatus.OK);
	}
}
