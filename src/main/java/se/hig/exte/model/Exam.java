package se.hig.exte.model;

import java.net.URL;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A model/entity class that represents an Exam. The fields of this class maps to columns in the
 * database.
 */
@Entity
public class Exam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "Name cannot be blank, empty or null")
	@Size(min = 2, message = "Name must be at least two characters long")
	private String filename;
	@NotNull(message = "Date cannot be null")
	private LocalDate date;
	@NotNull(message = "UnpublishDate cannot be null")
	private LocalDate unpublishDate;
	
	private boolean unpublished;
	private int courseId;
	
	// Only used for JPA/Spring, which is why it is declared with protected.
	protected Exam() {}
	
	/**
	 * Creates an {@code Exam} object.
	 * @param filename The name of the {@code Exam}. Must be at least two characters long and cannot be null.
	 * @param date The date of the {@code Exam}. Cannot be null.
	 * @param unpublishDate The date at which the {@code Exam} should be unpublished from the website. Cannot be null.
	 * @param pdfUrl The URL to the PDF file that corresponds to an {@code Exam}. Cannot be null.
	 * @param unpublished Set to true if this {@code Exam} should NOT be published on the website, otherwise false.
	 * @param courseId The ID of the {@link Course} to which an {@code Exam} belongs. Cannot be null.
	 */
	public Exam(String filename, LocalDate date, LocalDate unpublishDate, boolean unpublished, int courseId) {
		this.filename = filename;
		this.date = date;
		this.unpublishDate = unpublishDate;
		this.unpublished = unpublished;
		this.courseId = courseId;
	}

	/**
	 * Get the ID of this {@code Exam}.
	 * @return The ID of this {@code Exam}.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Get the name of this {@code Exam}.
	 * @return The name of this {@code Exam}.
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Get the date of this {@code Exam}.
	 * @return The date of this {@code Exam}.
	 */
	public LocalDate getDate() {
		return date;
	}
	
	/**
	 * Get the date at which this {@code Exam} will be unpublished from the website.
	 * @return The unpublish date of this {@code Exam}.
	 */
	public LocalDate getUnpublishDate() {
		return unpublishDate;
	}

	/**
	 * Get the {@link Course} to which this {@code Exam} belongs.
	 * @return The ID of the {@link Course} to which this {@code Exam} belongs.
	 */
	public int getCourseId() {
		return courseId;
	}
	
	/**
	 * Set the name of this {@code Exam}. Must be at least two characters long and not null.
	 * @param name The new name of this {@code Exam}.
	 */
	public void setFilename(String name) {
		this.filename = name;
	}

	/**
	 * Set the date of this {@code Exam}. Cannot be null.
	 * @param date The new date of this {@code Exam}.
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * Set the unpublish date of this {@code Exam}. Cannot be null.
	 * @param unpublishDate The new unpublish date of this {@code Exam}.
	 */
	public void setUnpublishDate(LocalDate unpublishDate) {
		this.unpublishDate = unpublishDate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "name: " + filename + "\nid: " + id + "\nDate: " + date + "\nunpublishDate: " + unpublishDate + "\nUnpublished: " + unpublished;
	}

	public boolean isUnpublished() {
		return unpublished;
	}

	public void setUnpublished(boolean unpublished) {
		this.unpublished = unpublished;
	}

	
}
