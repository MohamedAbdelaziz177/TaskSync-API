package com.SpringProj.todo.Exceptions;

import com.SpringProj.todo.Responses.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CodeNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleCodeNotValidException(CodeNotValidException ex){

        ApiResponse<Void> res = logErrorAndCreateFailureResponse(ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    @ExceptionHandler(PasswordsNotMatchedException.class)
    public ResponseEntity<ApiResponse<Void>> handlePasswordsNotMatchedException(PasswordsNotMatchedException ex){

        ApiResponse<Void> res = logErrorAndCreateFailureResponse(ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<Void>> handleAccessDeniedException(AccessDeniedException ex){

        ApiResponse<Void> res = logErrorAndCreateFailureResponse(ex);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleEntityNotFoundException(EntityNotFoundException ex){

        ApiResponse<Void> res = logErrorAndCreateFailureResponse(ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgumentException(IllegalArgumentException ex){

        ApiResponse<Void> res = logErrorAndCreateFailureResponse(ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResponse<Void>> handleNoSuchElementException(NoSuchElementException ex){

        ApiResponse<Void> res = logErrorAndCreateFailureResponse(ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ApiResponse<Void>> handleIOException(IOException ex){

        ApiResponse<Void> res = logErrorAndCreateFailureResponse(ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception ex){

        ApiResponse<Void> res = logErrorAndCreateFailureResponse(ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
    }





    private ApiResponse<Void> logErrorAndCreateFailureResponse(Exception ex){

        logger.error("error occurred", ex);

        ApiResponse<Void> res = new ApiResponse<>();

        res.setSuccess(Boolean.FALSE);
        res.setMessage(ex.getMessage());

        return res;
    }

}
