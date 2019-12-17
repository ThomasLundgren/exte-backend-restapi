package se.hig.exte.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.minidev.json.JSONObject;
import se.hig.exte.model.Exam;
import se.hig.exte.model.User;
import se.hig.exte.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {
	private LoginService loginService;
	
	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	/*
	@GetMapping("/{username}/{password}")
	public boolean loginAdmin(@PathVariable String username, @PathVariable String password) {
		return (loginService.login(username, password));
	}
	*/
	@PostMapping("/")
	public boolean loginAdmin(@RequestBody JSONObject json) {
		System.out.println("Email and password:");
		System.out.println(json.getAsString("email"));
		System.out.println(json.getAsString("password"));
		return (loginService.login(json.getAsString("email"), json.getAsString("password")));
	}
}
