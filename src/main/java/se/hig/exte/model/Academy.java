package se.hig.exte.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Academy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank(message = "Name cannot be blank, empty or null")
	private String name;
	@NotBlank(message = "Abbreviation cannot be blank, empty or null")
	private String abbreviation;
	@OneToMany(mappedBy = "academy")
	private List<Subject> subjects;
	
	// Only used for JPA/Spring, which is why it is declared with protected.
	protected Academy() {}

	public Academy(String name, String abbreviation) {
		this.name = name;
		this.abbreviation = abbreviation;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	@Override
	public String toString() {
		return "name: " + name + "\nid: " + id + "\nabbreviation: " + abbreviation;
	}
}
