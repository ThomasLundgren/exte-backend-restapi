package se.hig.exte.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

@Service
public class CookieHandler {
	private static HashMap<String, Session> sessions = new HashMap<String, Session>();
	private static final String COOKIE_NAME = "identifier";
	private final SettingsService settingsService;

	@Autowired
	public CookieHandler(SettingsService settingsService) {
		this.settingsService = settingsService;
	}

	/**
	 * Generates a new cookie for the user
	 *
	 * @param isSuperUser is true if the user should have super-admin privileges
	 * @return a ResponseCookie with the valid session settings
	 * @throws NoSuchAlgorithmException if a cookie has failed to be initialized.
	 */
	public ResponseCookie createCookie(boolean isSuperUser) throws NoSuchAlgorithmException {
		String generatedId = autoGenerateId();

		int cookieExpireSeconds = calculateCookieSessionTime();

		System.out.println("cookieExpireSeconds " + cookieExpireSeconds);

		ResponseCookie cookie = ResponseCookie.from(COOKIE_NAME, generatedId).maxAge(cookieExpireSeconds)
				.sameSite("Strict").secure(false).path("/").build();
		sessions.put(generatedId, new Session(isSuperUser, cookieExpireSeconds));
		System.out.println("CookieHandler: " + cookie);
		return cookie;
	}

	/**
	 * Checks if the user has a valid cookie with admin-privileges
	 *
	 * @param cookiesFromUser all the cookies fetched from the user
	 * @return true if the session is valid
	 */
	public boolean isValidAdminSession(Cookie[] cookiesFromUser) {
		Cookie klientResponseCookie = getSessionResponseCookie(cookiesFromUser);
		if (klientResponseCookie == null)
			return false;
		return checkIfServerSessionIsValid(klientResponseCookie.getValue());
	}

	/**
	 * Logs out the user
	 *
	 * @param cookiesFromUser all the cookies stored in the users browser.
	 */
	public void logout(Cookie[] cookiesFromUser) {
		Cookie cookie = getSessionResponseCookie(cookiesFromUser);
		if (cookie != null)
			sessions.remove(cookie.getValue());
	}

	private boolean checkIfServerSessionIsValid(String cookieValue) {
		Session serverSession = sessions.get(cookieValue);
		if (serverSession == null)
			return false;
		else if (serverSession.hasExpired()) {
			sessions.remove(cookieValue);
			return false;
		} else
			return true;
	}

	/**
	 * Checks if the user has a valid cookie with SuperAdmin-privileges
	 *
	 * @param cookiesFromUser all the cookies fetched from the user
	 * @return true if the session is valid
	 */
	public boolean isValidSuperSession(Cookie[] cookiesFromUser) {
		if (isValidAdminSession(cookiesFromUser))
			return sessions.get(getSessionResponseCookie(cookiesFromUser).getValue()).isSuperUser;
		return false;
	}

	private String autoGenerateId() throws NoSuchAlgorithmException {
		byte[] bytes = new byte[20 + (int) (Math.random() * (10))];
		SecureRandom.getInstanceStrong().nextBytes(bytes);
		return bytes.toString();
	}

	private Cookie getSessionResponseCookie(Cookie[] cookiesFromUser) {
		Cookie sessionResponseCookie = null;
		if (cookiesFromUser == null)
			return sessionResponseCookie;
		for (Cookie cookie : cookiesFromUser) {
			if (cookie.getName().equals(CookieHandler.COOKIE_NAME))
				sessionResponseCookie = cookie;
		}
		return sessionResponseCookie;
	}

	/**
	 * Removes all the sessions in the server that has been out-dated
	 */
	public void removeOldSessions() {
		List<String> sessionsToRemove = new ArrayList<String>();
		for (Map.Entry<String, Session> entry : sessions.entrySet()) {
			String key = entry.getKey();
			Session session = entry.getValue();
			if (session.hasExpired()) {
				sessionsToRemove.add(key);
			}
		}
		for (String key : sessionsToRemove) {
			sessions.remove(key);
		}
	}

	private int calculateCookieSessionTime() {
		System.out.println("Calc session time");
		return settingsService.getCurrentSettings().getCookieSessionMinutes() * 60;
	}

	/**
	 * Class representing a session.
	 *
	 * @author Sanna Lundqvist
	 *
	 */
	static class Session {
		private boolean isSuperUser;
		private LocalDateTime expiration;

		public Session(boolean isSuperUser, int expirationInSeconds) {
			this.isSuperUser = isSuperUser;
			this.expiration = LocalDateTime.now().plusSeconds(expirationInSeconds);
		}

		private boolean hasExpired() {
			return expiration.isBefore(LocalDateTime.now());
		}
	}
}
