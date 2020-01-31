package se.hig.exte.restcontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.minidev.json.JSONObject;
import se.hig.exte.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {
	private LoginService loginService;

	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	/**
	 * Tries to login a user
	 *
	 * @param response used to get all the stored cookies at the client
	 * @param request To see if the user and cookie are valid.
	 * @param json     A JSON-Object containing the users email as 'email' (String)
	 *                 and password as 'password' (String)
	 * @return boolean If the user was successfull in logging in.
	 */
	@RequestMapping("/")
	@PostMapping("/")
	public boolean loginAdmin(HttpServletResponse response, HttpServletRequest request, @RequestBody JSONObject json) {
		String username = json.getAsString("username").replaceAll(";", "").replaceAll("}", "").replaceAll("\"", "");
		String password = json.getAsString("password").replaceAll(";", "").replaceAll("}", "").replaceAll("\"", "");
		ResponseCookie cookie = loginService.login(username, password);

		if (cookie != null) {
			response.addHeader("Set-Cookie", cookie.toString());
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping("/logout")
	@PostMapping("/logout")
	public void logout(HttpServletRequest request) {
		loginService.logout(request.getCookies());
	}

}
