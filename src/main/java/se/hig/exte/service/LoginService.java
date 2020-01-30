package se.hig.exte.service;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import se.hig.exte.login.ILoginHandler;
import se.hig.exte.login.LoginHandler;

@Service
public class LoginService {
	private final ILoginHandler loginHandler;
	private final UserService userService;
	private final CookieHandler cookieHandler;

	/**
	 * @param loginHandler
	 */
	@Autowired
	public LoginService(UserService userService, CookieHandler cookieHandler) {
		this.userService = userService;
		this.cookieHandler = cookieHandler;
		loginHandler = new LoginHandler();
	}

	public ResponseCookie login(String username, String password) {
		ResponseCookie cookie = null;
		if (checkIfUserExists(username)) {
			boolean isLoggedIn = loginHandler.login(username.toString(), password.toString());
			if (isLoggedIn) {
				cookie = createCookie(userService.findByName(username).get(0).isSuperUser());
			}
		}
		return cookie;
		// return createCookie(userService.findByName(username).get(0).isSuperUser());
	}

	public void logout(Cookie[] cookies) {
		cookieHandler.logout(cookies);
	}

	private boolean checkIfUserExists(String username) {
		return !userService.findByName(username).isEmpty();
	}

	private ResponseCookie createCookie(boolean isSuperUser) {
		ResponseCookie cookie = null;
		try {
			cookie = cookieHandler.createCookie(isSuperUser);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return cookie;
	}

}
