package se.hig.exte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.Cookie;
import login.ILoginHandler;
import login.LoginHandler;
import login.SessionHandler;

@Service
public class LoginService {
	private final ILoginHandler loginHandler;
	private final SessionHandler sessionHandler;
	@Autowired
	private final UserService userService;
	private static final String COOKIE_NAME = "identifier";

	/**
	 * @param loginHandler
	 */

	public LoginService(UserService userService) {
		this.userService = userService;
		loginHandler = new LoginHandler();
		sessionHandler = new SessionHandler();
	}
	public Cookie login(String username, String password) {
		Cookie cookie = null;
		if(checkIfUserExists(username)) {
			boolean isLoggedIn = loginHandler.login(username, password);
			if(isLoggedIn) {
				cookie = createCookie(userService.findByName(username).get(0).isSuperUser());
			}
		}
		return cookie;
	}

	private boolean checkIfUserExists(String username) {
		return !userService.findByName(username).isEmpty();
	}
	
	private Cookie createCookie(boolean isSuperUser){
		Cookie cookie = null;
		try {
			cookie = new Cookie(COOKIE_NAME, sessionHandler.addSession(isSuperUser));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return cookie;
	}

}
