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
	private String name;
	private String courseCode;
	private String subjectCode;
	
	// Only used for JPA/Spring, which is why it is declared with protected.
	protected Course() {}
	
	public Course(String name, String courseCode, String subjectCode) {
		this.name = name;
		this.courseCode = courseCode;
		this.subjectCode = subjectCode;
	}

	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}
	
	public String getSubjectCode() {
		return subjectCode;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	@Override
	public String toString() {
		return "name: " + name + "\ncourseCode: " + courseCode + "\nid: " + id + "\nsubjectCode: " + subjectCode;
	}


}
