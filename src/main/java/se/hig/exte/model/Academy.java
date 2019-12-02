package se.hig.exte.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Academy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String abbreviation;
	
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

	public void setId(int id) {
		this.id = id;
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
