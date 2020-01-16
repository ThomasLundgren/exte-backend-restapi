package se.hig.exte.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.hig.exte.model.Settings;
import se.hig.exte.service.CookieHandler;
import se.hig.exte.service.SettingsService;

@RestController
@RequestMapping("/settings")
public class SettingsController {

	private final SettingsService settingsService;
	private final CookieHandler cookieHandler;

	@Autowired
	public SettingsController(SettingsService settingsService, CookieHandler cookieHandler) {
		this.settingsService = settingsService;
		this.cookieHandler = cookieHandler;
	}

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

	@GetMapping("/all")
	public ResponseEntity<List<Settings>> getAllSettings(HttpServletRequest request) {
		if (cookieHandler.isValidSuperSession(request.getCookies())) {
			List<Settings> settings = settingsService.findAllSettingsSorted();
			return new ResponseEntity<List<Settings>>(settings, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Settings>>(HttpStatus.UNAUTHORIZED);
		}
	}

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
