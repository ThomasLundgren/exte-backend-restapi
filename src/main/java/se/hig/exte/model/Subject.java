package se.hig.exte.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Subject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Size(min = 2, max = 2)
	@NotBlank(message = "Code cannot be blank, empty or null")
	private String code;
	@Size(min = 3)
	@NotBlank(message = "Name cannot be blank, empty or null")
	private String name;
	@ManyToOne
	@JoinColumn(name = "AcademyId")
	@NotNull(message = "Academy cannot be null")
	private Academy academy;
	
	// Only used for JPA/Spring, which is why it is declared with protected.
	protected Subject() {
	}

	public Subject(String code, String name, Academy academy) {
		this.code = code;
		this.name = name;
		this.academy = academy;
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
	
	public Academy getAcademy() {
		return academy;
	}
	
	public void setAcademy(Academy academy) {
		this.academy = academy;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "code: " + code + "\nname: " + name + "\nid: " + id + "\nadademyId: ";
	}
	
}
