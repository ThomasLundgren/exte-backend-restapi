package se.hig.exte.restcontroller;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
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
	 * @param request
	 * @param json     A JSON-Object containing the users email as 'email' (String)
	 *                 and password as 'password' (String)
	 * @return
	 */
	@RequestMapping("/")
	@PostMapping("/")
	public boolean loginAdmin(HttpServletResponse response, HttpServletRequest request, @RequestBody JSONObject json) {
		String email = json.getAsString("email").replaceAll(";", "").replaceAll("}", "").replaceAll("\"", "");
		String password = json.getAsString("password").replaceAll(";", "").replaceAll("}", "").replaceAll("\"", "");
		ResponseCookie cookie = loginService.login(email, password);

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

	private void printAllCookies(HttpServletRequest request) {
		Cookie[] cookiesFromUser = request.getCookies();
		if (cookiesFromUser != null) {
			System.out.println(Arrays.stream(cookiesFromUser).map(c -> c.getName() + "=" + c.getValue())
					.collect(Collectors.joining(", ")));
		}
	}
}
