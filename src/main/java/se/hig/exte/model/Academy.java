package se.hig.exte.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * A model/entity class that represents an Academy. The fields of this class
 * maps to columns in the database.
 */
@Entity
public class Academy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "Name cannot be empty or null")
	@Size(min = 2)
	private String name;
	@NotBlank(message = "Abbreviation cannot be empty or null")
	@Size(min = 2, max = 5, message = "Abbreviation must be between two and five characters long")
	private String abbreviation;
	private boolean unpublished;

	// Only used for JPA/Spring, which is why it is declared with protected.
	protected Academy() {
	}

	/**
	 * Creates an {@code Academy} object.
	 * 
	 * @param name         The name of the {@code Academy}. Must be at least two
	 *                     characters long and cannot be null.
	 * @param abbreviation The abbreviation of the {@code Academy}. The abbreviation
	 *                     should be between two and five characters long.
	 */
	public Academy(String name, String abbreviation) {
		this.name = name;
		this.abbreviation = abbreviation;
	}

	/**
	 * Get the ID of this {@code Academy}.
	 * 
	 * @return The ID of this {@code Academy}.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Get the name of this {@code Academy}.
	 * 
	 * @return The name of this {@code Academy}.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the abbreviation of this {@code Academy}.
	 * 
	 * @return The abbreviation of this {@code Academy}.
	 */
	public String getAbbreviation() {
		return abbreviation;
	}

	public boolean getUnpublished() {
		return unpublished;
	}

	/**
	 * Set the name of this {@code Academy}. The name must be at least two
	 * characters long and cannot be null.
	 * 
	 * @param name The name of this {@code Academy}.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Set the abbreviation of this {@code Academy}. The abbreviation must be at
	 * between two and five characters long and cannot be null.
	 * 
	 * @param abbreviation The abbreviation of this {@code Academy}.
	 */
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "name: " + name + "\nid: " + id + "\nabbreviation: " + abbreviation;
	}

	public void setUnpublished(boolean unpublished) {
		this.unpublished = unpublished;

	}
}
