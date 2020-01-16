package se.hig.exte.model;

import java.time.LocalDateTime;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import se.hig.exte.util.MyLocalDateTimeConverter;

@Entity
public class Settings {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Min(1)
	private int cookieSessionMinutes;
	@NotBlank(message = "Home page HTML cannot be blank")
	private String HomePageHtml;
	@NotBlank(message = "Home page HTML cannot be blank")
	private String AboutPageHtml;
	@Min(1)
	private int unpublishTimeYears;
//	 Might need a converter
//	@Convert(converter = MyLocalDateTimeConverter.class)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private LocalDateTime created;

	protected Settings() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCookieSessionMinutes() {
		return cookieSessionMinutes;
	}

	public void setCookieSessionMinutes(int cookieSessionMinutes) {
		this.cookieSessionMinutes = cookieSessionMinutes;
	}

	public String getHomePageHtml() {
		return HomePageHtml;
	}

	public void setHomePageHtml(String homePageHtml) {
		HomePageHtml = homePageHtml;
	}

	public String getAboutPageHtml() {
		return AboutPageHtml;
	}

	public void setAboutPageHtml(String aboutPageHtml) {
		AboutPageHtml = aboutPageHtml;
	}

	public int getUnpublishTimeYears() {
		return unpublishTimeYears;
	}

	public void setUnpublishTimeYears(int unpublishTimeYears) {
		this.unpublishTimeYears = unpublishTimeYears;
	}
	
	public LocalDateTime getCreated() {
		return created;
	}
	
}
