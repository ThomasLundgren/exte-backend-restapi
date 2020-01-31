package se.hig.exte.restcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.rest.core.annotation.RestResource;
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
import se.hig.exte.service.CookieHandler;
import se.hig.exte.service.CrudService;
import se.hig.exte.service.UserService;

/**
 * This class is a RestController class responsible for mapping HTTP requests
 * under the /users path. It contains mappings of end-points to
 * {@link CrudService}s that operate on {@link User} records in the database.
 */
@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;
	private final CookieHandler cookieHandler;

	/**
	 * Creates an {@code UserController} object.
	 * 
	 * @param userService   The {@link CrudService} class used to perform all
	 *                      services exposed in this RestController.
	 * @param cookieHandler object responsible for handling authentication.
	 */
	@Autowired
	public UserController(UserService userService, CookieHandler cookieHandler) {
		this.userService = userService;
		this.cookieHandler = cookieHandler;
	}

	/**
	 * Creates a {@link User} and stores it in the database.
	 * 
	 * @param request To see if the user and cookie are valid.
	 * @param user The {@link User} to add in the form of a JSON-object in the POST
	 *             request.
	 * @return A {@code ResponseEntity} object containing the saved {@link User} and
	 *         an HTTP status code.
	 */
	@PostMapping("/")
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user, HttpServletRequest request) {
		if (cookieHandler.isValidSuperSession(request.getCookies())) {
			User savedUser = userService.save(user);
			return new ResponseEntity<User>(savedUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
		}
	}

	/**
	 * Fetches the {@link User} object with the given ID from the database.
	 * 
	 * @param id The ID of the {@link User} to fetch.
	 * @param request To see if the user and cookie are valid.
	 * @return The {@link User} with the given ID.
	 */
	@GetMapping("/{id}")
	public User getUser(@PathVariable String id, HttpServletRequest request) {
		if (cookieHandler.isValidAdminSession(request.getCookies())) {
			int userId = Integer.parseInt(id);
			return userService.findById(userId);
		}
		return null;
	}

	/**
	 * Fetches all {@link User} records from the database and returns them as a
	 * {@code ResponseEntity} object. List of {@link User} objects is automatically
	 * converted to JSON using Spring Boot's {@code HttpMessageConverter} and put in
	 * the {@code ResponseEntity}'s body.
	 * 
	 * @param request To see if the user and cookie are valid.
	 * @return A {@code ResponseEntity} object containing the fetched {@link User}
	 *         objects.
	 */
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUsers(HttpServletRequest request) {
		if (cookieHandler.isValidSuperSession(request.getCookies())) {
			return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
		} else {
			return new ResponseEntity<List<User>>(HttpStatus.UNAUTHORIZED);
		}
	}

	/**
	 * Updates the {@link User} object with the given ID in the database.
	 * 
	 * @param request To see if the user and cookie are valid.
	 * @param user The {@link User} to update in the form of a JSON-object in the
	 *             POST request.
	 * @return A {@code ResponseEntity} object containing the updated {@link User}
	 *         and an HTTP status code.
	 */
	@PatchMapping("/")
	public ResponseEntity<User> updateUser(@Valid @RequestBody User user, HttpServletRequest request) {
		if (cookieHandler.isValidSuperSession(request.getCookies())) {
			User savedUser = userService.save(user);
			return new ResponseEntity<User>(savedUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
		}
	}

	/**
	 * Deletes the {@link User} object with the given ID from the database.
	 * 
	 * @param id The ID of the {@link User} to delete.
	 * @param request To see if the user and cookie are valid.
	 */
	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable int id, HttpServletRequest request) {
		if (cookieHandler.isValidSuperSession(request.getCookies())) {
			userService.deleteById(id);
		}
	}

	/**
	 * Returns true or false dependent on the contents of the
	 * {@link HttpServletRequest} cookies.
	 * 
	 * @param request the {@link HttpServletRequest}
	 * @return {@code true} if the request's contains a valid cookie with
	 *         Administration-privileges, otherwise false.
	 */
	@GetMapping("/is/admin")
	public boolean isLoggedInAsAdmin(HttpServletRequest request) {
		return cookieHandler.isValidAdminSession(request.getCookies());
	}

	/**
	 * Returns true or false dependent on the contents of the
	 * {@link HttpServletRequest} cookies.
	 * 
	 * @param request the {@link HttpServletRequest}
	 * @return {@code true} if the request's contains a valid cookie with
	 *         SuperUser-privileges, otherwise false.
	 */
	@GetMapping("/is/superuser")
	public boolean isLoggedInAsSuperUser(HttpServletRequest request) {
		return cookieHandler.isValidSuperSession(request.getCookies());
	}

}
