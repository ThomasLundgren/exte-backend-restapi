package se.hig.exte.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.hig.exte.model.User;
import se.hig.exte.repository.UserRepository;

@Service
public class UserService implements IService<User> {

	private final UserRepository userRepo;
	
	/**
	 * @param userRepo
	 */
	@Autowired
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public User add(User user) {
		return userRepo.save(user);
	}

	@Override
	public User findById(int id) {
		return userRepo.findById(id);
	}

	@Override
	public void deleteById(int id) {
		userRepo.deleteById(id);
	}
}
