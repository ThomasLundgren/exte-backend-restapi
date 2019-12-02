package se.hig.exte.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String courseCode;
	private String subjectCode;
	
	// Only used for JPA/Spring, which is why it is declared with protected.
	protected Course() {}
	
	public Course(String courseCode, String subjectCode) {
		this.courseCode = courseCode;
		this.subjectCode = subjectCode;
	}

	public int getId() {
		return id;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	@Override
	public String toString() {
		return "courseCode: " + courseCode + "\nid: " + id + "\nsubjectCode: " + subjectCode;
	}
}
