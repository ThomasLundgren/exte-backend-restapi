package se.hig.exte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.hig.exte.model.Exam;
import se.hig.exte.model.User;
import se.hig.exte.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {
	private final UserRepository userRepository;

	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@PostMapping("/add")
	public ResponseEntity<User> create(@RequestBody User user) {
		User savedUser = userRepository.save(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable String id) {
		int userId = Integer.parseInt(id);
		return userRepository.findById(userId);
	}
	
}
