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
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A model/entity class that represents a Subject. The fields of this class maps
 * to columns in the database.
 */
@Entity
public class Tag {

	@Id
	@Column(name = "tagId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Name cannot be blank")
	@Pattern(regexp = "^[\\s\\wåöäÅÖÄ,-]+$", message = "Name must contain only alphabetic characters (a-ö), whitespace, commas and hyphens")
	private String tagName;
	
	@ManyToMany(mappedBy = "tags", cascade = CascadeType.ALL) 
	private Set<Course> courses = new HashSet<Course>();

	// Only used for JPA/Spring, which is why it is declared with protected.
	protected Tag() {
	}

	public Tag(String tagName) {
		this.tagName = tagName;
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
	 * Get the name of this {@code Subject}.
	 * 
	 * @return The name of this {@code Subject}.
	 */
	public String getTagName() {
		return tagName;
	}
	
	@JsonIgnore
	public Set<Course> getCourses() {
        return courses;
    }

	/**
	 * Set the code of this {@code Subject}. Must be at least three characters long
	 * and cannot be null or whitespace.
	 * 
	 * @param name The name this {@code Subject}.
	 */
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "tagName: " + tagName + "\nid: " + id;
	}

}
