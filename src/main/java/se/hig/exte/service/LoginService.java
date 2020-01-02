package se.hig.exte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.hig.exte.login.ILoginHandler;
import se.hig.exte.login.LoginHandler;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.Cookie;

@Service
public class LoginService {
	private final ILoginHandler loginHandler;
	@Autowired
	private final UserService userService;

	/**
	 * @param loginHandler
	 */

	public LoginService(UserService userService) {
		this.userService = userService;
		loginHandler = new LoginHandler();
	}
	
	public Cookie login(String username, String password) {
		Cookie cookie = null;
		System.out.println(username);
		if(checkIfUserExists(username)) {
			boolean isLoggedIn = loginHandler.login(username.toString(), password.toString());
			if(isLoggedIn) {
				cookie = createCookie(userService.findByName(username).get(0).isSuperUser());
			}
		}
		return cookie;
	}
	
	public void logout(Cookie[] cookies) {
		CookieHandler.logout(cookies);
	}

	private boolean checkIfUserExists(String username) {
		return !userService.findByName(username).isEmpty();
	}
	
	private Cookie createCookie(boolean isSuperUser){
		Cookie cookie = null;
		try {
			cookie = CookieHandler.createCookie(isSuperUser);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return cookie;
	}

}
