package interview.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidProductDataException extends ApiException {
    public InvalidProductDataException() {
        super(HttpStatus.METHOD_NOT_ALLOWED);
    }
}
