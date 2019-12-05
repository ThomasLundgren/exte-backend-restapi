package se.hig.exte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.hig.exte.model.User;
import se.hig.exte.service.IService;
import se.hig.exte.service.UserService;

/**
 * This class is a RestController class responsible for mapping HTTP requests for the /users end-point.
 */
@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;

	/**
	 * Create a {@code UserController} object.
	 * @param userService The {@link IService} class used to perform all services exposed in this RestController. 
	 */
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * Creates a user and stores it in the database.
	 * @param user The {@link User} to add.
	 * @return A {@code ResponseEntity} object containing the saved {@link User} and an HTTP status code.
	 */
	@PostMapping("/")
	public ResponseEntity<User> create(@RequestBody User user) {
		User savedUser = userService.add(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable String id) {
		int userId = Integer.parseInt(id);
		return userService.findById(userId);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable int id) {
		userService.deleteById(id);
	}
	
	
}
