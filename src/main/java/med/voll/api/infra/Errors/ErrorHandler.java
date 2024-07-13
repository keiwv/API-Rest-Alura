package med.voll.api.infra.Errors;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleError404()
    {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException ex)
    {
        var errors = ex.getFieldErrors().stream().map(dataErrorValidation::new).toList();

        return ResponseEntity.badRequest().body(errors);
    }

    private record dataErrorValidation(String field, String error)
    {
        public dataErrorValidation(FieldError error)
        {
            this(error.getField(), error.getDefaultMessage());
        }

    }
}

