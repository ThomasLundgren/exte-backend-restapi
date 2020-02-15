package se.hig.exte.model;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A model/entity class that represents a Course. The fields of this class maps
 * to columns in the database.
 */
@Entity
public class Course {

	@Id
	@Column(name = "courseId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "Name cannot be blank")
	@Size(min = 3, message = "Name must be at least three characters long")
	@Pattern(regexp = "^[\\s\\wåöäÅÖÄ,-]+$", message = "Name must contain only alphabetic characters (a-ö), whitespace, commas and hyphens")
	private String name;
	@NotBlank(message = "Course code cannot be blank")
	@Size(min = 6, max = 7, message = "Course code must be between six and seven characters long")
	@Pattern(regexp = "^([A-ZÅÄÖ]{3}[0-9]{3})([A-ZÅÄÖ])?", message = "Course code must start with three uppercase letters followed by three digits and ending with an optional uppercase letter")
	private String courseCode;
	private boolean unpublished;
	private int subjectId;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "Courses_To_Tags",
			joinColumns = @JoinColumn(name = "courseId"),
			inverseJoinColumns = @JoinColumn(name = "tagId"))
	Set<Tag> tags = new HashSet<Tag>();
	
	// Only used for JPA/Spring, which is why it is declared with protected.
	protected Course() {
	}

	/**
	 * Creates a {@code Course} object.
	 * 
	 * @param name        The name of this {@code Course}. Must be at least two
	 *                    characters long and cannot be null.
	 * @param courseCode  The course code of this {@code Course}. Must be between
	 *                    six and seven characters long.
	 * @param subjectId   The ID of the {@link Subject} to which this {@code Course}
	 *                    belongs.
	 * @param unpublished Boolean to see if the course is unpublished
	 */
	public Course(String name, String courseCode, boolean unpublished, int subjectId) {
		this.name = name;
		this.courseCode = courseCode;
		this.unpublished = unpublished;
		this.subjectId = subjectId;
	}
	
	/**
	 * Creates a {@code Course} object.
	 * 
	 * @param name        The name of this {@code Course}. Must be at least two
	 *                    characters long and cannot be null.
	 * @param courseCode  The course code of this {@code Course}. Must be between
	 *                    six and seven characters long.
	 * @param subjectId   The ID of the {@link Subject} to which this {@code Course}
	 *                    belongs.
	 * @param unpublished Boolean to see if the course is unpublished
	 */
	public Course(String name, String courseCode, boolean unpublished, int subjectId, Set<Tag> tags) {
		this.name = name;
		this.courseCode = courseCode;
		this.unpublished = unpublished;
		this.subjectId = subjectId;
		this.tags = tags;
	}
	/**
	 * Get the ID of this {@code Course}.
	 * 
	 * @return The ID of this {@code Course}.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Get the name of this {@code Course}.
	 * 
	 * @return The name of this {@code Course}.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the {@link Subject} to which this {@code Course} belongs.
	 * 
	 * @return The ID of the {@link Subject} to which this {@code Course} belongs.
	 */
	public int getSubjectId() {
		return subjectId;
	}

	/**
	 * Get the course code of this {@code Course}.
	 * 
	 * @return The course code of this {@code Course}.
	 */
	public String getCourseCode() {
		return courseCode;
	}

	public boolean isUnpublished() {
		return unpublished;
	}

	public Set<Tag> getTags() {
		return tags;
	}
	
	/**
	 * Set the name of this {@code Course}. Must be at least two characters long.
	 * 
	 * @param name The new name of this {@code Course}.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Set the course code of this {@code Course}. Must be between six and seven
	 * characters long.
	 * 
	 * @param courseCode The new course code of this {@code Course}.
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public void setUnpublished(boolean unpublished) {
		this.unpublished = unpublished;
	}
	
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "name: " + name + "\ncourseCode: " + courseCode + "\nid: " + id + "\nsubjectId: " + subjectId;
	}
}
