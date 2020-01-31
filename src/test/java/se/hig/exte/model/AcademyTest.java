package se.hig.exte.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AcademyTest {

	private Academy academy;
	private Validator validator;
	private Set<ConstraintViolation<Academy>> violations;

	@BeforeEach
	private void setup() {
		academy = new Academy();
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	@AfterEach
	private void tearDown() {
		academy = null;
		validator = null;
	}

	@Test
	void whenNameLessThanThreeLetters_validatorValidationFails() {
		this.academy = new Academy("", "AHA");
		violations = validator.validate(academy);
		assertFalse(violations.isEmpty());
		this.academy = new Academy("h", "AHA");
		violations = validator.validate(academy);
		assertFalse(violations.isEmpty());
		this.academy = new Academy("ha", "AHA");
		violations = validator.validate(academy);
		assertFalse(violations.isEmpty());
	}

	@Test
	void whenNameMoreThanThreeLetters_validatorValidationSucceeds() {
		this.academy = new Academy("aha", "AHA");
		violations = validator.validate(academy);
		assertTrue(violations.isEmpty());
	}

	@Test
	void whenNameContainsSpecialCharacters_validatorValidationFails() {
		this.academy = new Academy("aha%", "AHA");
		violations = validator.validate(academy);
		assertFalse(violations.isEmpty());
		this.academy = new Academy("aha&", "AHA");
		violations = validator.validate(academy);
		assertFalse(violations.isEmpty());
		this.academy = new Academy("aha/", "AHA");
		violations = validator.validate(academy);
		assertFalse(violations.isEmpty());
		this.academy = new Academy("aha!", "AHA");
		violations = validator.validate(academy);
		assertFalse(violations.isEmpty());
	}

}
