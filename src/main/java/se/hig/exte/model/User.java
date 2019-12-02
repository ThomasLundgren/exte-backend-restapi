package se.hig.exte.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private boolean isSuperUser;
	
	public User() {
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

	public void setId(int id) {
		this.id = id;
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
