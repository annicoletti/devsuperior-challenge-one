package br.com.nicoletti.devsuperior.resource;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.nicoletti.devsuperior.entity.to.StandardError;
import br.com.nicoletti.devsuperior.exceptions.DatabaseException;
import br.com.nicoletti.devsuperior.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	private HttpStatus status;

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Resource not found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());

		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> databaseIntegrity(DatabaseException e, HttpServletRequest request) {
		status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Database exception");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());

		return ResponseEntity.status(status).body(err);
	}



}
