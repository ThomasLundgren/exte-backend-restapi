package login;

public interface ILoginHandler {

	public boolean login(String username, String password);
	public void logout(String username);
	
}
