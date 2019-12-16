package se.hig.exte.restcontroller;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.servlet.http.Cookie;

import net.minidev.json.JSONObject;

public class SessionHandler {
	private static JSONObject cookies = new JSONObject();
	public static final String COOKIE_NAME = "identifier";

	public static String addSession(boolean isSuperUser) throws NoSuchAlgorithmException {
		String generatedId = autoGenerateId();
		cookies.appendField(generatedId, isSuperUser);
		return generatedId;
	}
	
	public static boolean isValidSession(String sessionId) {
		return cookies.containsValue(sessionId);
	}

	public static boolean isValidSuperSession(String sessionId) {
		if(isValidSession(sessionId))
			return cookies.get(sessionId).equals(true);
		return false;
	}

	public static void removeSession(String username) {
		cookies.remove(username);
	}

	private static String autoGenerateId() throws NoSuchAlgorithmException {
		byte[] bytes = new byte[20 +  (int)(Math.random()*(10))];
		SecureRandom.getInstanceStrong().nextBytes(bytes);
		return bytes.toString();
	}

	public static String extractCookies(Cookie[] cookiesFromUser) {
		String cookieValue = "";
		if(cookiesFromUser == null)
			return cookieValue;
		for (Cookie cookie : cookiesFromUser) {
			if(cookie.getName().equals(SessionHandler.COOKIE_NAME))
				cookieValue = cookie.getValue();
		}
		return cookieValue;
	}
}
