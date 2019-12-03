package se.hig.exte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.hig.exte.model.User;
import se.hig.exte.repository.UserRepository;

/**
 * A service that stores an {@link User} object to persistent storage.
 */
@Service
public class AddUserService {

	private final UserRepository userRepository;
	private User user;

	/**
	 * Creates an instance of this service.
	 * @param userRepository The {@link UserRepository} object used to store to persistent storage.
	 * @param user The {@link User} object to be stored.
	 */
	@Autowired
	public AddUserService(UserRepository userRepository, User user) {
		this.userRepository = userRepository;
		this.user = user;
	}
	
	/**
	 * Executes this service.
	 * @param user The {@link User} object to store in persistent storage.
	 */
	public void execute() {
		userRepository.save(user);
	}

}
