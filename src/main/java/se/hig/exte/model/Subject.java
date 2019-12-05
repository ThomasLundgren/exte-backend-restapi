package se.hig.exte.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A model/entity class that represents a Subject. The fields of this class maps to columns in the
 * database.
 */
@Entity
public class Subject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Size(min = 2, max = 2)
	@NotBlank(message = "Code cannot be blank, empty or null")
	private String code;
	@Size(min = 3)
	@NotBlank(message = "Name cannot be blank, empty or null")
	private String name;
	@ManyToOne
	@JoinColumn(name = "AcademyId")
	@NotNull(message = "Academy cannot be null")
	private Academy academy;
	
	// Only used for JPA/Spring, which is why it is declared with protected.
	protected Subject() {
	}

	/**
	 * Creates a {@code Subject} object.
	 * @param code The code of the {@code Subject}. Must be two characters long and cannot be null or whitespace.
	 * @param name The name of the {@code Subject}. Must be at least three characters long and cannot be null or whitespace.
	 * @param academy The {@link Academy} to which this {@code Subject} belongs. Cannot be null.
	 */
	public Subject(String code, String name, Academy academy) {
		this.code = code;
		this.name = name;
		this.academy = academy;
	}

	/**
	 * Get the ID of this {@code Subject}.
	 * @return The ID of this {@code Subject}.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Get the code of this {@code Subject}.
	 * @return The code of this {@code Subject}.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Get the name of this {@code Subject}.
	 * @return The name of this {@code Subject}.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the {@link Academy} to which this {@code Subject} belongs.
	 * @return The @{@link Academy} to which this {@code Subject} belongs.
	 */
	public Academy getAcademy() {
		return academy;
	}
	
	/**
	 * Set the {@link Academy} to which this {@code Subject}. Cannot be null.
	 * @param academy The {@link Academy} to which this {@code Sunject} belongs.
	 */
	public void setAcademy(Academy academy) {
		this.academy = academy;
	}

	/**
	 * Set the code of this {@code Subject}. Must be two characters long and cannot be null or whitespace.
	 * @param code The code this {@code Subject}.
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Set the code of this {@code Subject}. Must be at least three characters long and cannot be null or whitespace.
	 * @param code The name this {@code Subject}.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "code: " + code + "\nname: " + name + "\nid: " + id + "\nadademyId: ";
	}
	
}
