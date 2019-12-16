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
		loginHandler = new LoginHandler();
	}
	public boolean login(String username, String password) {
		if(checkIfUserExists(username)) {
			return loginHandler.login(username, password);
		}else {
			return false;
		}
	}
	
	private boolean checkIfUserExists(String username) {
		return !userService.findByName(username).isEmpty();
	}
	
}
