package se.hig.exte.model;

import java.net.URL;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
//@Table(name = "Exam")
public class Exam {

	/*
	 * Tutorial for making ENTITIES with Spring:
	 * https://spring.io/guides/gs/accessing-data-jpa/
	 * Spring uses the JPA-API which is a part of the Java standard library.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;	
	private String name;
	private LocalDate date;
	
	/*
	 * @Column is only used if the name of the column in the SQL Table differs from the
	 * instance variable name.
	 */
//	@Column(name = "PdfUrl")
	private URL pdfUrl; /* 
							* address to FTP-resource on the form:
							* scheme:[//authority][/path][?query][#fragment]
							* Example: 
							* ftp://theuser:thepassword@someauthority:21
							*/
//	@ManyToOne
//	@JoinColumn
//	private Course course;
	
	private int courseId;
	
	// Only used for JPA/Spring, which is why it is declared with protected.
	public Exam() {}
	
	// This constructor is the one we use.
	public Exam(String name, LocalDate date, URL pdfUrl, int courseId) {
		this.name = name;
		this.date = date;
		this.pdfUrl = pdfUrl;
		this.courseId = courseId;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LocalDate getDate() {
		return date;
	}

	public URL getPdfUrl() {
		return pdfUrl;
	}
	
	@Override
	public String toString() {
		return "name: " + name + "\nid: " + id + "\nDate: " + date + "\nURL: " + pdfUrl;
	}

	
}
