package se.hig.exte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.hig.exte.model.User;
import se.hig.exte.repository.UserRepository;

@Service
public class AddUserService {

	private final UserRepository userRepository;

	@Autowired
	public AddUserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public void execute(User user) {
		userRepository.save(user);
	}

}
