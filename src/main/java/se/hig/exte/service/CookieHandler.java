package se.hig.exte.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;

import javax.servlet.http.Cookie;

public class CookieHandler {
	private static HashMap<String, Session> sessions = new HashMap<String, Session>();
	private static final String COOKIE_NAME = "identifier";
	private static final int COOKIE_EXPIRE_SECONDS = 60;

	public static Cookie createCookie(boolean isSuperUser) throws NoSuchAlgorithmException {
		String generatedId = autoGenerateId();
		Cookie cookie = new Cookie(COOKIE_NAME, generatedId);
		cookie.setMaxAge(COOKIE_EXPIRE_SECONDS);
		sessions.put(generatedId, new Session(isSuperUser, COOKIE_EXPIRE_SECONDS));
		return cookie;
	}
	
	public static boolean isValidSession(Cookie[] cookiesFromUser) {
		Cookie klientCookie = getSessionCookie(cookiesFromUser);		
		return checkIfServerSessionIsValid(klientCookie.getValue());
	}
	
	private static boolean checkIfServerSessionIsValid(String cookieValue) {
		Session serverSession = sessions.get(cookieValue);
		if(serverSession == null)
			return false;
		else if(serverSession.hasExpired()) {
			sessions.remove(cookieValue);
			return false;
		}else
			return true;
	}

	public static boolean isValidSuperSession(Cookie[] cookiesFromUser) {
		if(isValidSession(cookiesFromUser))
			return sessions.get(getSessionCookie(cookiesFromUser).getValue()).isSuperUser;
		return false;
	}

	private static String autoGenerateId() throws NoSuchAlgorithmException {
		byte[] bytes = new byte[20 +  (int)(Math.random()*(10))];
		SecureRandom.getInstanceStrong().nextBytes(bytes);
		return bytes.toString();
	}

	private static Cookie getSessionCookie(Cookie[] cookiesFromUser) {
		Cookie sessionCookie = null;
		if(cookiesFromUser == null)
			return sessionCookie;
		for (Cookie cookie : cookiesFromUser) {
			if(cookie.getName().equals(CookieHandler.COOKIE_NAME))
				sessionCookie = cookie;
		}
		return sessionCookie;
	}
	
	static class Session{
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
