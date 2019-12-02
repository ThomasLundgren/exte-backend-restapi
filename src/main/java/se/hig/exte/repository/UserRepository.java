package se.hig.exte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.hig.exte.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findById(int id);
}
