package account.errors;


import account.payload.response.CustomErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;


@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<Object> handleUserExists(UserExistsException e, WebRequest request) {
        CustomErrorResponse body = new CustomErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                e.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BreachedPasswordException.class)
    public ResponseEntity<Object> handleBreachedPassword(BreachedPasswordException e, WebRequest request) {
        CustomErrorResponse body = new CustomErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                e.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SamePasswordException.class)
    public ResponseEntity<Object> handleSamePassword(SamePasswordException e, WebRequest request) {
        CustomErrorResponse body = new CustomErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                e.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedAccessAttemptException.class)
    public ResponseEntity<Object> handleUnauthorizedPasswordChange(UnauthorizedAccessAttemptException e, WebRequest request) {
        CustomErrorResponse body = new CustomErrorResponse(
                LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                e.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrorResponse body = new CustomErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(body, headers, status);
    }

}
