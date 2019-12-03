package se.hig.exte.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String courseCode;
//	@ManyToOne
//	@JoinColumn
//	private Subject subject;
	private int subjectId;
	
	// Only used for JPA/Spring, which is why it is declared with protected.
	protected Course() {}
	
	public Course(String name, String courseCode, int subjectId) {
		this.name = name;
		this.courseCode = courseCode;
		this.subjectId = subjectId;
	}

	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}
	
	public int getSubjectId() {
		return subjectId;
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

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	@Override
	public String toString() {
		return "name: " + name + "\ncourseCode: " + courseCode + "\nid: " + id + "\nsubjectCode: " + subjectId;
	}


}
