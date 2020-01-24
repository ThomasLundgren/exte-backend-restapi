package se.hig.exte.restcontroller;

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
	public static final String MAX_UPLOAD_SIZE_ERROR = "Maximum file size exceeded";
	public static final String MULTIPART_ERROR = "Multipart error";
	public static final String DATA_INTEGRITY_VIOLATION_ERROR = "Data integrity violation";

	@ExceptionHandler({ MethodArgumentNotValidException.class, MaxUploadSizeExceededException.class,
			MultipartException.class, DataIntegrityViolationException.class })
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
		} else if (e instanceof DataIntegrityViolationException) {
			HttpStatus status = HttpStatus.BAD_REQUEST;
			DataIntegrityViolationException dive = (DataIntegrityViolationException) e;
			return handleDataIntegrityViolationException(dive, request, headers, status);
		} else {
			HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
			return handleExceptionInternal(e, null, headers, status, request);
		}
	}

	private ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException manve,
			WebRequest request, HttpHeaders headers, HttpStatus status) {
		ApiError errors = createApiError(ENTITY_VALIDATION_ERROR, manve);
		manve.getBindingResult().getAllErrors().forEach(error -> {
			String errorMessage = error.getDefaultMessage();
			errors.addError(errorMessage);
		});
		return handleExceptionInternal(manve, errors, headers, status, request);
	}

	private ResponseEntity<ApiError> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException musee,
			WebRequest request, HttpHeaders headers, HttpStatus status) {
		ApiError errors = createApiError(MAX_UPLOAD_SIZE_ERROR, musee.getMostSpecificCause());
		return handleExceptionInternal(musee, errors, headers, status, request);
	}

	private ResponseEntity<ApiError> handleMultiPartException(MultipartException mpe, WebRequest request,
			HttpHeaders headers, HttpStatus status) {
		ApiError errors = createApiError(MULTIPART_ERROR, mpe.getMostSpecificCause());
		/*
		 * Bypass handleExceptionInternal call since status is
		 * HttpStatus.INTERNAL_SERVER_ERROR and we don't want generic exception handling
		 * here.
		 */
		return new ResponseEntity<ApiError>(errors, headers, status);
	}

	private ResponseEntity<ApiError> handleDataIntegrityViolationException(DataIntegrityViolationException dive,
			WebRequest request, HttpHeaders headers, HttpStatus status) {
		ApiError errors = createApiError(DATA_INTEGRITY_VIOLATION_ERROR, dive.getMostSpecificCause());
		return handleExceptionInternal(dive, errors, headers, status, request);
	}

	private ResponseEntity<ApiError> handleExceptionInternal(Exception ex, ApiError body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
		}
		return new ResponseEntity<ApiError>(body, headers, status);
	}

	/*
	 * It's important to supply the right Throwable from the Exception's stack trace
	 * to this method because the error message can differ a lot depending on which
	 * Throwable is passed in. Often, it is best to call
	 * org.springframework.core.NestedRuntimeException.getMostSpecificCause() and
	 * pass that in. It often generates the most specific error messages.
	 */
	private ApiError createApiError(String errorType, Throwable throwable) {
		ApiError errors = new ApiError(errorType);
		errors.addError(throwable.getMessage());
		return errors;
	}

}
