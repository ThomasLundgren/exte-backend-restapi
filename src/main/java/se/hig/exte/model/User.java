package se.hig.exte.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * A model/entity class that represents a User. The fields of this class maps to columns in the
 * database.
 */
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "Name cannot be blank, empty or null")
	@Size(min = 3, message = "Name must be at least three characters long")
	private String name;
	private boolean isSuperUser;
	
	// Only used for JPA/Spring, which is why it is declared with protected.
	protected User() {
	}

	/**
	 * Creates a {@code User} object.
	 * @param name The name of the {@code User}. Must be at least two characters long and cannot be null or whitespace.
	 * @param isSuperUser True if the {@code User} should be a super user. Otherwise false.
	 */
	public User(String name, boolean isSuperUser) {
		this.name = name;
		this.isSuperUser = isSuperUser;
	}

	/**
	 * Get the ID of this {@code User}.
	 * @return The ID of this {@code User}.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Get the name of this {@code User}.
	 * @return The name of this {@code User}.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns true if this {@code User} is a super user. Otherwise false.
	 * @return True if this {@code User} is a super user. Otherwise false.
	 */
	public boolean isSuperUser() {
		return isSuperUser;
	}

	/**
	 * Set the name of this {@code User}. Must be at least two characters long and cannot be null or whitespace.
	 * @param name The new name of this {@code User}.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Set the privilege of this {@code User}. Set it to true to grant privileges to make other {@code User}s super users.
	 * @param isSuperUser The new super user status of this {@code User}.
	 */
	public void setSuperUser(boolean isSuperUser) {
		this.isSuperUser = isSuperUser;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "name: " + name + "\nid: " + id + "\nisSuperUser: " + isSuperUser;
	}
}
