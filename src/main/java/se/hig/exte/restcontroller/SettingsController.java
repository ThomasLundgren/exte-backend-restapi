package se.hig.exte.restcontroller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
	
	@Autowired
	public SettingsController(SettingsService settingsService) {
		this.settingsService = settingsService;
	}
	
	@GetMapping("/")
	public ResponseEntity<Settings> getSettings() {
		Settings settings = settingsService.getSettings();
		return new ResponseEntity<Settings>(settings, HttpStatus.OK);
	}
	
	@PatchMapping("/")
	public ResponseEntity<Settings> update(@RequestBody Settings settings, HttpServletRequest request) {
		if (CookieHandler.isValidSuperSession(request.getCookies())) {
			Settings newSettings = settingsService.update(settings);
			return new ResponseEntity<Settings>(newSettings, HttpStatus.OK);
		} else {
			return new ResponseEntity<Settings>(HttpStatus.UNAUTHORIZED);
		}
	}
}
