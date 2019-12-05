package se.hig.exte.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "Name cannot be blank, empty or null")
	private String name;
	private boolean isSuperUser;
	
	// Only used for JPA/Spring, which is why it is declared with protected.
	protected User() {
	}

	public User(int id, String name, boolean isSuperUser) {
		this.id = id;
		this.name = name;
		this.isSuperUser = isSuperUser;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isSuperUser() {
		return isSuperUser;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSuperUser(boolean isSuperUser) {
		this.isSuperUser = isSuperUser;
	}

	@Override
	public String toString() {
		return "name: " + name + "\nid: " + id + "\nisSuperUser: " + isSuperUser;
	}
}
