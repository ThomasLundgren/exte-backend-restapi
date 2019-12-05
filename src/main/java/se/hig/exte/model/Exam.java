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
	@ManyToOne
	@JoinColumn(name = "CourseId")
	@NotNull(message = "Course cannot be null")
	private Course course;
	
	// Only used for JPA/Spring, which is why it is declared with protected.
	protected Exam() {}
	
	/**
	 * Creates an {@code Exam} object.
	 * @param name The name of the {@code Exam}.
	 * @param date The date of the {@code Exam}.
	 * @param unpublishDate The date at which the {@code Exam} should be unpublished from the website.
	 * @param pdfUrl The URL to the PDF file that corresponds to an {@code Exam}.
	 * @param course The {@link Course} to which an {@code Exam} belongs.
	 */
	public Exam(String name, LocalDate date, LocalDate unpublishDate, URL pdfUrl, Course course) {
		this.name = name;
		this.date = date;
		this.unpublishDate = unpublishDate;
		this.pdfUrl = pdfUrl;
		this.course = course;
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

	public Course getCourse() {
		return course;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setUnpublishDate(LocalDate unpublishDate) {
		this.unpublishDate = unpublishDate;
	}

	public void setPdfUrl(URL pdfUrl) {
		this.pdfUrl = pdfUrl;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "name: " + name + "\nid: " + id + "\nDate: " + date + "\nunpublishDate: " + unpublishDate + "\nURL: " + pdfUrl;
	}

	
}
