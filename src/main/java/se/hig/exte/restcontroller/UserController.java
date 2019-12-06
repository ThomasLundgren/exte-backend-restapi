package se.hig.exte.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.hig.exte.model.User;
import se.hig.exte.service.CrudService;
import se.hig.exte.service.UserService;

/**
 * This class is a RestController class responsible for mapping HTTP requests under the /users path.
 * It contains contains mappings of end-points to {@link CrudService}s that operate on {@link User} records
 * in the database.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private final UserService userService;

	/**
	 * Create a {@code UserController} object.
	 * @param userService The {@link CrudService} class used to perform all services exposed in this RestController. 
	 */
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * Creates a {@link User} and stores it in the database.
	 * @param user The {@link User} to add in the form of a JSON-object in the POST request.
	 * @return A {@code ResponseEntity} object containing the saved {@link User} and an HTTP status code.
	 */
	@PostMapping("/")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		User savedUser = userService.save(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable String id) {
		int userId = Integer.parseInt(id);
		return userService.findById(userId);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllCourses() {
		return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable int id) {
		userService.deleteById(id);
	}
	
	@PatchMapping("/")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		User savedUser = userService.save(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.OK);
	}
}
