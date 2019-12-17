package se.hig.exte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import login.ILoginHandler;
import login.LoginHandler;

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
		this.loginHandler = new LoginHandler();
	}
	public boolean login(String username, String password) {
		System.out.println("Trying to log in...");
		boolean isLoggedIn = false;
		if(checkIfUserExists(username)) {
			System.out.println("user exists in db...");
			isLoggedIn = loginHandler.login(username, password);
		}
		return isLoggedIn;
	}
	
	private boolean checkIfUserExists(String username) {
		return !userService.findByName(username).isEmpty();
	}
	
}
