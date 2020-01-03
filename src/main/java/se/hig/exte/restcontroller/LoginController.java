package se.hig.exte.restcontroller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.minidev.json.JSONObject;
import se.hig.exte.service.LoginService;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/login")
public class LoginController {
	private LoginService loginService;
	
	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	@RequestMapping("/")
	@PostMapping("/")
	public boolean loginAdmin(HttpServletResponse response, HttpServletRequest request, @RequestBody JSONObject json) {
		printAllCookies(request);
		Cookie cookie = loginService.login(json.getAsString("email"), json.getAsString("password"));
		if(cookie != null) {
			response.addCookie(cookie);
			return true;
		}else {
			return false;
		}
	}

	private void printAllCookies(HttpServletRequest request) {
		Cookie[] cookiesFromUser = request.getCookies();
		if (cookiesFromUser != null) {
			System.out.println(Arrays.stream(cookiesFromUser)
					.map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", ")));
		}
	}
}
