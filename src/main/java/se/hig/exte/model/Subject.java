package se.hig.exte.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * A model/entity class that represents a Subject. The fields of this class maps
 * to columns in the database.
 */
@Entity
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Size(min = 2, max = 2, message = "Subject code must be two characters")
	@NotBlank(message = "Subject code cannot be blank")
	@Pattern(regexp = "[A-ZÅÄÖ]", message = "Subject code must contain only uppercase alphabetic characters")
	private String code;
	@Size(min = 3, message = "Name must be at least three characters long")
	@NotBlank(message = "Name cannot be blank")
	@Pattern(regexp = "[a-zåäöA-ZÅÄÖ]", message = "Name must contain only alphabetic characters (a-ö)")
	private String name;
	private boolean unpublished;
	private int academyId;

	// Only used for JPA/Spring, which is why it is declared with protected.
	protected Subject() {
	}

	/**
	 * Creates a {@code Subject} object.
	 * 
	 * @param code      The code of the {@code Subject}. Must be two characters long
	 *                  and cannot be null or whitespace.
	 * @param name      The name of the {@code Subject}. Must be at least three
	 *                  characters long and cannot be null or whitespace.
	 * @param academyId The ID of the {@link Academy} to which this {@code Subject}
	 *                  belongs.
	 */
	public Subject(String code, String name, boolean published, int academyId) {
		this.code = code;
		this.name = name;
		this.unpublished = published;
		this.academyId = academyId;
	}

	/**
	 * Get the ID of this {@code Subject}.
	 * 
	 * @return The ID of this {@code Subject}.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Get the code of this {@code Subject}.
	 * 
	 * @return The code of this {@code Subject}.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Get the name of this {@code Subject}.
	 * 
	 * @return The name of this {@code Subject}.
	 */
	public String getName() {
		return name;
	}

	public boolean isUnpublished() {
		return unpublished;
	}

	/**
	 * Get the ID of the {@link Academy} to which this {@code Subject} belongs.
	 * 
	 * @return The ID of the {@link Academy} to which this {@code Subject} belongs.
	 */
	public int getAcademyId() {
		return academyId;
	}

	/**
	 * Set the code of this {@code Subject}. Must be two characters long and cannot
	 * be null or whitespace.
	 * 
	 * @param code The code this {@code Subject}.
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Set the code of this {@code Subject}. Must be at least three characters long
	 * and cannot be null or whitespace.
	 * 
	 * @param name The name this {@code Subject}.
	 */
	public void setName(String name) {
		this.name = name;
	}

	public void setUnpublished(boolean unpublished) {
		this.unpublished = unpublished;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "code: " + code + "\nname: " + name + "\nid: " + id + "\nadademyId: ";
	}

}
