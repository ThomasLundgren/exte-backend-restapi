package se.hig.exte.model;

import java.util.ArrayList;
import java.util.List;

public class ApiError {

	private String errorType = "Unspecified error";
	private List<String> errors = new ArrayList<String>();

	public ApiError(String errorType) {
		this.errorType = errorType;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public void addError(String error) {
		errors.add(error);
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

}
