package se.hig.exte.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A model/entity class that represents a Course. The fields of this class maps to columns in the
 * database.
 */
@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "Name cannot be empty or null")
	@Size(min = 2, message = "Name must be at least two characters long")
	private String name;
	@NotBlank(message = "Course code cannot be empty or null")
	@Size(min = 6, max = 7, message = "Course code must be between six and seven characters long")
	private String courseCode;
	@ManyToOne
	@JoinColumn(name = "SubjectID")
	@NotNull(message = "Subject cannot be null")
	private Subject subject;
	@OneToMany(mappedBy = "course")
	private List<Exam> exams;
	
	// Only used for JPA/Spring, which is why it is declared with protected.
	protected Course() {}
	
	/**
	 * Creates a {@code Course} object.
	 * @param name The name of this {@code Course}. Must be at least two characters long and cannot be null.
	 * @param courseCode The course code of this {@code Course}. Must be between six and seven characters long.
	 * @param subject The {@link Subject} to which this {@code Course} belongs.
	 */
	public Course(String name, String courseCode, Subject subject) {
		this.name = name;
		this.courseCode = courseCode;
		this.subject = subject;
	}

	/**
	 * Get the ID of this {@code Course}.
	 * @return The ID of this {@code Course}.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Get the name of this {@code Course}.
	 * @return The name of this {@code Course}.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the {@link Subject} to which this {@code Course} belongs.
	 * @return The {@link Subject} to which this {@code Course} belongs.
	 */
	public Subject getSubject() {
		return subject;
	}

	/**
	 * Get the course code of this {@code Course}.
	 * @return The course code of this {@code Course}.
	 */
	public String getCourseCode() {
		return courseCode;
	}

	/**
	 * Set the name of this {@code Course}. Must be at least two characters long.
	 * @param name The new name of this {@code Course}.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Set the course code of this {@code Course}. Must be between six and seven characters long.
	 * @param name The new course code of this {@code Course}.
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 * Set the {@link Subject} to which this {@code Course} should belong. Cannot be null.
	 * @param name The new {@link Subject} to which this {@code Course} should belong.
	 */
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "name: " + name + "\ncourseCode: " + courseCode + "\nid: " + id + "\nsubjectCode: ";
	}
}
