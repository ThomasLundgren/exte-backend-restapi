package se.hig.exte.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import se.hig.exte.model.User;
import se.hig.exte.repository.UserRepository;

@Service
public class UserService implements CrudService<User> {

	private final UserRepository userRepo;

	@Autowired
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public User save(User user) {
		return userRepo.save(user);
	}

	@Override
	public User findById(int id) {
		return userRepo.findById(id);
	}

	@Override
	public List<User> findAll() {
		return userRepo.findAll();
	}

	@Override
	public void deleteById(int id) {
		userRepo.deleteById(id);
	}

	public List<User> findByName(String name) {
		return userRepo.findByName(name);
	}

	@Scheduled(cron = "0 * * * * *")
	public void removeOldSessions() {
		try {
			CookieHandler.createCookie(false);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		CookieHandler.removeOldSessions();
	}

}
