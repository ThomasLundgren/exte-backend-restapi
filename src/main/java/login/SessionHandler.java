package login;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import net.minidev.json.JSONObject;

public class SessionHandler {
	private static JSONObject cookies = new JSONObject();
	
	public String addSession(boolean isSuperUser) throws NoSuchAlgorithmException {
		String generatedId = autoGenerateId();
		cookies.appendField(generatedId, isSuperUser);
		return generatedId;
	}
	public boolean isValidSession(String sessionId) {
		return cookies.containsValue(sessionId);
	}
	public boolean isValidSuperSession(String sessionId) {
		if(isValidSession(sessionId))
			return cookies.get(sessionId).equals(true);
		return false;
	}
	public void removeSession(String username) {
		cookies.remove(username);
	}
	private String autoGenerateId() throws NoSuchAlgorithmException {
		byte[] bytes = new byte[20 +  (int)(Math.random()*(10))];
		SecureRandom.getInstanceStrong().nextBytes(bytes);
		return bytes.toString();
	}
}
