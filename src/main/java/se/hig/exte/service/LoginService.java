package se.hig.exte.service;

import login.ILoginHandler;
import login.LoginHandler;

public class LoginService {
	private final ILoginHandler loginHandler;
	
	/**
	 * @param loginHandler
	 */
	
	public LoginService() {
		this.loginHandler = new LoginHandler();
	}
	public void login(String username, String password) {
		
	}
	
}
