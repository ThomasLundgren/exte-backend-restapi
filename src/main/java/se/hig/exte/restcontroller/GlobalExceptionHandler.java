package se.hig.exte.restcontroller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.util.WebUtils;

import se.hig.exte.model.ApiError;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	public static final String ENTITY_VALIDATION_ERROR = "Entity validation error";
	public static final String MAX_UPLOAD_SIZE_ERROR = "File size exceeded the maximum limit. Maximum limit set to 5MB";
	public static final String MULTIPART_ERROR = "Server failed to upload file due to a multipart exception. Please try again";
//	public static final String SQL_CONSTRAINT_ERROR = "SQL Constraint error";
	public static final String DATA_INTEGRITY_VIOLATION_ERROR = "Data Integrity Violation Error";

	@ExceptionHandler({ MethodArgumentNotValidException.class, MaxUploadSizeExceededException.class,
			MultipartException.class, SQLIntegrityConstraintViolationException.class, DataIntegrityViolationException.class })
	public ResponseEntity<ApiError> handleException(Exception e, WebRequest request) {
		HttpHeaders headers = new HttpHeaders();
		if (e instanceof MethodArgumentNotValidException) {
			HttpStatus status = HttpStatus.BAD_REQUEST;
			MethodArgumentNotValidException manve = (MethodArgumentNotValidException) e;
			return handleMethodArgumentNotValidException(manve, request, headers, status);
		} else if (e instanceof MaxUploadSizeExceededException) {
			HttpStatus status = HttpStatus.PAYLOAD_TOO_LARGE;
			MaxUploadSizeExceededException musee = (MaxUploadSizeExceededException) e;
			return handleMaxUploadSizeExceededException(musee, request, headers, status);
		} else if (e instanceof MultipartException) {
			HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
			MultipartException mpe = (MultipartException) e;
			return handleMultiPartException(mpe, request, headers, status);
		} 
//		else if (e instanceof SQLIntegrityConstraintViolationException) {
//			HttpStatus status = HttpStatus.BAD_REQUEST;
//			SQLIntegrityConstraintViolationException sqle = (SQLIntegrityConstraintViolationException) e;
//			return handleSQLIntegrityConstraintViolationException(sqle, request, headers, status);
//		} 
		else if (e instanceof DataIntegrityViolationException) {
			HttpStatus status = HttpStatus.BAD_REQUEST;
			DataIntegrityViolationException dive = (DataIntegrityViolationException) e;
			System.out.println("dive message: " + dive.getMessage());
			System.out.println("cause: " + dive.getCause().getMessage());
			System.out.println("most specific cause: " + dive.getMostSpecificCause().getMessage());
			return handleDataIntegrityViolationException(dive, request, headers, status);
		} else {
			HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
			return handleExceptionInternal(e, null, headers, status, request);
		}
	}

	private ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException manve,
			WebRequest request, HttpHeaders headers, HttpStatus status) {
		List<String> errors = createErrorList(ENTITY_VALIDATION_ERROR, manve);
		manve.getBindingResult().getAllErrors().forEach(error -> {
			String errorMessage = error.getDefaultMessage();
			errors.add(errorMessage);
		});
		System.out.println(errors.toString());
		return handleExceptionInternal(manve, new ApiError(errors), headers, status, request);
	}
	
	private ResponseEntity<ApiError> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException musee,
			WebRequest request, HttpHeaders headers, HttpStatus status) {
		List<String> errors = createErrorList(MAX_UPLOAD_SIZE_ERROR, musee.getMostSpecificCause());
		return handleExceptionInternal(musee, new ApiError(errors), headers, status, request);
	}

	private ResponseEntity<ApiError> handleMultiPartException(MultipartException mpe, WebRequest request,
			HttpHeaders headers, HttpStatus status) {
		List<String> errors = createErrorList(MULTIPART_ERROR, mpe.getMostSpecificCause());
		// bypass handleExceptionInternal call since status is HttpStatus.INTERNAL_SERVER_ERROR
		return new ResponseEntity<ApiError>(new ApiError(errors), headers, status);
	}
	
//	private ResponseEntity<ApiError> handleSQLIntegrityConstraintViolationException(
//			SQLIntegrityConstraintViolationException sqle, WebRequest request, HttpHeaders headers, HttpStatus status) {
//		List<String> errors = createErrorList(SQL_CONSTRAINT_ERROR, sqle);
//		return handleExceptionInternal(sqle, new ApiError(errors), headers, status, request);
//	}
	
	private ResponseEntity<ApiError> handleDataIntegrityViolationException(DataIntegrityViolationException dive,
			WebRequest request, HttpHeaders headers, HttpStatus status) {
		List<String> errors = createErrorList(DATA_INTEGRITY_VIOLATION_ERROR, dive.getMostSpecificCause());
		return handleExceptionInternal(dive, new ApiError(errors), headers, status, request);
	}
	
	private ResponseEntity<ApiError> handleExceptionInternal(Exception ex, ApiError body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
		}
		return new ResponseEntity<ApiError>(body, headers, status);
	}
	
	private List<String> createErrorList(String error1, Throwable throwable) {
		ArrayList<String> errors = new ArrayList<String>();
		errors.add(error1);
		errors.add(throwable.getMessage());
		return errors;
	}

}
