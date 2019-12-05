package se.hig.exte.model;

import java.net.URL;
import java.time.LocalDate;

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
	private String name;
	@NotNull(message = "Date cannot be null")
	private LocalDate date;
	@NotNull(message = "UnpublishDate cannot be null")
	private LocalDate unpublishDate;
	@NotNull(message = "PdfUrl cannot be null")
	private URL pdfUrl; /* 
							* address to FTP-resource on the form:
							* scheme:[//authority][/path][?query][#fragment]
							* Example: 
							* ftp://theuser:thepassword@someauthority:21
							*/
	
	private int courseId;
	
	// Only used for JPA/Spring, which is why it is declared with protected.
	protected Exam() {}
	
	/**
	 * Creates an {@code Exam} object.
	 * @param name The name of the {@code Exam}. Must be at least two characters long and cannot be null.
	 * @param date The date of the {@code Exam}. Cannot be null.
	 * @param unpublishDate The date at which the {@code Exam} should be unpublished from the website. Cannot be null.
	 * @param pdfUrl The URL to the PDF file that corresponds to an {@code Exam}. Cannot be null.
	 * @param courseId The ID of the {@link Course} to which an {@code Exam} belongs. Cannot be null.
	 */
	public Exam(String name, LocalDate date, LocalDate unpublishDate, URL pdfUrl, int courseId) {
		this.name = name;
		this.date = date;
		this.unpublishDate = unpublishDate;
		this.pdfUrl = pdfUrl;
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
	public String getName() {
		return name;
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
	 * Get the URL of the PDF file to which this {@code Exam} corresponds.
	 * @return The URL of the PDF file to which this {@code Exam} corresponds.
	 */
	public URL getPdfUrl() {
		return pdfUrl;
	}

	/**
	 * Get the {@link Course} to which this {@code Exam} belongs.
	 * @return The ID of the {@link Course} to which this {@code Exam} belongs.
	 */
	public int getCourse() {
		return courseId;
	}
	
	/**
	 * Set the name of this {@code Exam}. Must be at least two characters long and not null.
	 * @param name The new name of this {@code Exam}.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Set the date of this {@code Exam}. Cannot be null.
	 * @param name The new date of this {@code Exam}.
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * Set the unpublish date of this {@code Exam}. Cannot be null.
	 * @param name The new unpublish date of this {@code Exam}.
	 */
	public void setUnpublishDate(LocalDate unpublishDate) {
		this.unpublishDate = unpublishDate;
	}

	/**
	 * Set the PDF file URL that corresponds to this {@code Exam}. Cannot be null.
	 * @param name The new PDF file URL that corresponds to this {@code Exam}.
	 */
	public void setPdfUrl(URL pdfUrl) {
		this.pdfUrl = pdfUrl;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "name: " + name + "\nid: " + id + "\nDate: " + date + "\nunpublishDate: " + unpublishDate + "\nURL: " + pdfUrl;
	}

	
}
