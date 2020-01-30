package se.hig.exte.service;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import se.hig.exte.login.ILoginHandler;
import se.hig.exte.login.LoginHandler;

@Service
public class LoginService {
	private final ILoginHandler loginHandler;
	private final UserService userService;
	private final CookieHandler cookieHandler;
	private HashMap<String, Integer> failedLogins;
	private final int MAX_LOGIN_FAILS = 3;

	/**
	 * 
	 * @param loginHandler
	 */
	@Autowired
	public LoginService(UserService userService, CookieHandler cookieHandler) {
		this.userService = userService;
		this.cookieHandler = cookieHandler;
		loginHandler = new LoginHandler();
		this.failedLogins = new HashMap<String, Integer>();
	}

	public ResponseCookie login(String username, String password) {
		ResponseCookie cookie = null;

		if (isAllowedToTryToLogin(username)) {
			boolean isLoggedIn = false;
			if (checkIfUserExists(username)) {
				isLoggedIn = loginHandler.login(username.toString(), password.toString());
				if (isLoggedIn) {
					cookie = createCookie(userService.findByName(username).get(0).isSuperUser());
				}
			}
			if (!isLoggedIn) {
				handleFailedLoginTry(username);
			}
		} else {
			throw new IllegalAccessError();
		}
		return cookie;

		// return createCookie(userService.findByName(username).get(0).isSuperUser());
	}

	private boolean isAllowedToTryToLogin(String username) {
		return !((this.failedLogins.get(username) != null) && (this.failedLogins.get(username) >= MAX_LOGIN_FAILS));
	}

	private void handleFailedLoginTry(String username) {
		int nbrOfFaildLogins = 1;
		if (this.failedLogins.get(username) != null) {
			nbrOfFaildLogins = this.failedLogins.get(username) + 1;
		}
		this.failedLogins.put(username, nbrOfFaildLogins);
		System.out.println("nbr of faild i handle : " + nbrOfFaildLogins);

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

	/**
	 * Removes all the failed login tries every 5 minutes.
	 * 
	 */

	@Scheduled(cron = "0 */5 * * * *")
	public void removeOldLoginTries() {
		failedLogins = new HashMap<String, Integer>();
	}
}
