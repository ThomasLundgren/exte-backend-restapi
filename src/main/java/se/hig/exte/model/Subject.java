package se.hig.exte.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Subject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String code;
	private String name;
	private int academyId;
	
	// Only used for JPA/Spring, which is why it is declared with protected.
	protected Subject() {
	}

	public Subject(String code, String name, int academyId) {
		this.code = code;
		this.name = name;
		this.academyId = academyId;
	}

	public int getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public int getAcademyId() {
		return academyId;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	@Override
	public String toString() {
		return "code: " + code + "\nname: " + name + "\nid: " + id + "\nadademyId: " + academyId;
	}
	
}
