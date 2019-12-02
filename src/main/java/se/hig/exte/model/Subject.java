package se.hig.exte.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Subject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String code;
	private String name;
	private int academyId;
	
	public Subject() {
	}

	public Subject(String code, String name, int academyId) {
		this.code = code;
		this.name = name;
		this.academyId = academyId;
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
		return "code: " + code + "\nname: " + name + "\nadademyId: " + academyId;
	}
	
}
