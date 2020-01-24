package se.hig.exte.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 * A model/entity class that represents App Settings. The fields of this class
 * maps to columns in the database.
 */
@Entity
public class Settings {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Min(value = 5, message = "Cookie session time must be greater than or equal to 5 minutes")
	private int cookieSessionMinutes;
	@NotBlank(message = "Home page HTML cannot be blank")
	private String homePageHtml;
	@NotBlank(message = "Home page HTML cannot be blank")
	private String aboutPageHtml;
	@Min(value = 1, message = "Unpublish time must be greater than or equal to 1 year")
	private int unpublishTimeYears;
	@Generated(GenerationTime.INSERT)
	@Column(name = "created", updatable = false)
	private LocalDateTime created;

	protected Settings() {
	}

	/**
	 * Creates a {@code Settings} object. ID and a creation timestamp are generated
	 * and should not be provided.
	 * 
	 * @param cookieSessionMinutes Specifies how long a login session should last.
	 * @param homePageHtml         Sets the HTML code of the "home" page.
	 * @param aboutPageHTML        Sets the HTML code of the "about" page.
	 * @param unpublishTimeYears   The number of years an {@link Exam} should be
	 *                             stored before becoming unpublished.
	 */
	public Settings(int cookieSessionMinutes, String homePageHtml, String aboutPageHTML, int unpublishTimeYears) {
		this.cookieSessionMinutes = cookieSessionMinutes;
		this.homePageHtml = homePageHtml;
		this.aboutPageHtml = aboutPageHTML;
		this.unpublishTimeYears = unpublishTimeYears;
	}

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
		return homePageHtml;
	}

	public void setHomePageHtml(String homePageHtml) {
		this.homePageHtml = homePageHtml;
	}

	public String getAboutPageHtml() {
		return aboutPageHtml;
	}

	public void setAboutPageHtml(String aboutPageHtml) {
		this.aboutPageHtml = aboutPageHtml;
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
