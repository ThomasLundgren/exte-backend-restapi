package se.hig.exte.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.springframework.http.ResponseCookie;

public class CookieHandler {
	private static HashMap<String, Session> sessions = new HashMap<String, Session>();
	private static final String COOKIE_NAME = "identifier";
	private static final int COOKIE_EXPIRE_SECONDS = 60 * 2;

	/**
	 * Generates a new cookie for the user
	 * 
	 * @param isSuperUser is true if the user should have super-admin privileges
	 * @return a ResponseCookie with the valid session settings
	 * @throws NoSuchAlgorithmException if a cookie has failed to be initialized.
	 */
	public static ResponseCookie createCookie(boolean isSuperUser) throws NoSuchAlgorithmException {
		String generatedId = autoGenerateId();

		// ResponseCookie cookie = new ResponseCookie(COOKIE_NAME, generatedId);
		// cookie.setMaxAge(COOKIE_EXPIRE_SECONDS);

		ResponseCookie cookie = ResponseCookie.from(COOKIE_NAME, generatedId).maxAge(COOKIE_EXPIRE_SECONDS)
				.sameSite("Lax").secure(false).path("/").build();
		sessions.put(generatedId, new Session(isSuperUser, COOKIE_EXPIRE_SECONDS));
		return cookie;
	}

	/**
	 * Checks if the user has a valid cookie with admin-privileges
	 * 
	 * @param cookiesFromUser all the cookies fetched from the user
	 * @return true if the session is valid
	 */
	public static boolean isValidAdminSession(Cookie[] cookiesFromUser) {
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
	public static void logout(Cookie[] cookiesFromUser) {
		Cookie cookie = getSessionResponseCookie(cookiesFromUser);
		if (cookie != null)
			sessions.remove(cookie.getValue());
	}

	private static boolean checkIfServerSessionIsValid(String cookieValue) {
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
	public static boolean isValidSuperSession(Cookie[] cookiesFromUser) {
		/*
		 * if (isValidAdminSession(cookiesFromUser)) return
		 * sessions.get(getSessionResponseCookie(cookiesFromUser).getValue()).
		 * isSuperUser; return false;
		 */
		return true;
	}

	private static String autoGenerateId() throws NoSuchAlgorithmException {
		byte[] bytes = new byte[20 + (int) (Math.random() * (10))];
		SecureRandom.getInstanceStrong().nextBytes(bytes);
		return bytes.toString();
	}

	private static Cookie getSessionResponseCookie(Cookie[] cookiesFromUser) {
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
	public static void removeOldSessions() {
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
