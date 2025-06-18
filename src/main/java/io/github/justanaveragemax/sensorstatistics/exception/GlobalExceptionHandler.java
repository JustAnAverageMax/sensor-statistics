package io.github.justanaveragemax.sensorstatistics.exception;

import io.github.justanaveragemax.sensorstatistics.dto.FieldViolationDto;
import io.github.justanaveragemax.sensorstatistics.dto.response.ApplicationErrorResponse;
import io.github.justanaveragemax.sensorstatistics.dto.response.ValidationErrorResponse;
import io.github.justanaveragemax.sensorstatistics.util.ConstantsUtil;
import jakarta.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<ApplicationErrorResponse> handleInternalError(Exception ex) {
    return handleGenericException(HttpStatus.INTERNAL_SERVER_ERROR, ex);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ValidationErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
    List<FieldViolationDto> violations = ex.getBindingResult().getFieldErrors().stream()
        .map(error -> new FieldViolationDto(error.getField(), error.getDefaultMessage()))
        .toList();

    return ResponseEntity.badRequest().body(new ValidationErrorResponse(violations));
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ValidationErrorResponse> handleConstraintViolations(ConstraintViolationException ex) {
    List<FieldViolationDto> violations = ex.getConstraintViolations().stream()
        .map(error -> new FieldViolationDto(error.getPropertyPath().toString(), error.getMessage()))
        .toList();

    return ResponseEntity.badRequest().body(new ValidationErrorResponse(violations));
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ApplicationErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
    final String message = String.format("Invalid value '%s' for parameter '%s'. Expected date in format yyyy-MM-dd.",
        ex.getValue(), ex.getName());

    return ResponseEntity.badRequest().body(ApplicationErrorResponse.builder()
        .code(HttpStatus.BAD_REQUEST.value())
        .description(message)
        .timestamp(LocalDate.now(ConstantsUtil.DEFAULT_ZONE_ID).toString())
        .build());
  }

  private ResponseEntity<ApplicationErrorResponse> handleGenericException(HttpStatus httpStatus, Throwable ex) {
    ApplicationErrorResponse rs = ApplicationErrorResponse.builder()
        .code(httpStatus.value())
        .timestamp(LocalDateTime.now().toString())
        .description(ex.getMessage())
        .build();

    return ResponseEntity.status(httpStatus).body(rs);
  }

}
