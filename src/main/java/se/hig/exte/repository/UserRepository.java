package se.hig.exte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import se.hig.exte.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	/*
	 * The Repository interface is the Spring equivalent of DAO classes.
	 */
	
	List<User> findByName(String name);
	
	User findById(int id);
}
