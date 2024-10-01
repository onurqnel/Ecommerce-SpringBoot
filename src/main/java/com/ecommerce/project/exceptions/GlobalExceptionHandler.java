package com.ecommerce.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * GlobalExceptionHandler handles exceptions globally across all controllers.
 * <p>
 * This class uses the @RestControllerAdvice annotation to make it a centralized place for exception handling.
 * Specifically, it handles validation errors that occur during method argument validation
 * (for example, when @Valid or @Validated is used in a controller method).
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles MethodArgumentNotValidException exceptions.
     * <p>
     * This method intercepts validation errors and collects all the field validation messages
     * into a map where the key is the field name and the value is the error message.
     *
     * @param e the MethodArgumentNotValidException thrown when a method argument fails validation.
     * @return a map containing the field name as the key and the corresponding validation error message as the value.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValidHandler(MethodArgumentNotValidException e) {
        // Initialize an empty map to store the error messages
        Map<String, String> response = new HashMap<>();

        /*
         * The getBindingResult() method retrieves the result of the validation.
         * getAllErrors() returns a list of all validation errors.
         * For each error, we check if it is a FieldError (since it pertains to a specific field),
         * then extract the field name and the corresponding error message.
         */
        e.getBindingResult().getAllErrors().forEach((error) -> {
            // Cast the error to FieldError to extract specific field information
            String fieldName = ((FieldError) error).getField();
            // Get the default error message associated with the validation failure
            String errorMessage = error.getDefaultMessage();
            // Put the field name and its associated error message into the response map
            response.put(fieldName, errorMessage);
        });

        // Return the response map containing all the field error messages
        return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
    }
}
