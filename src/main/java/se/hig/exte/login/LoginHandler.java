package se.hig.exte.login;

public class LoginHandler implements ILoginHandler{

	@Override
	public boolean login(String username, String password) {
		return LDAP.authenticateHigLdap(username, password);
	}

	@Override
	public void logout(String username) {
		// TODO Auto-generated method stub
		
	}

}
