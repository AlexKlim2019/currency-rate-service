package currencyrateservice.exception;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@NoArgsConstructor
@ControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException exception) {
        log.debug(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<Object> handleInvalidInputException(InvalidInputException exception) {
        log.debug(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(ExternalDataSourceException.class)
    public ResponseEntity<Object> handleInvalidInputException(ExternalDataSourceException exception) {
        log.debug(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

    @ExceptionHandler(JsonParsingException.class)
    public ResponseEntity<Object> handleInvalidInputException(JsonParsingException exception) {
        log.debug(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }
}
