package interview.exceptions;

import org.springframework.http.HttpStatus;

public class ProductAlreadyExistsException extends ApiException {
    public ProductAlreadyExistsException() {
        super(HttpStatus.METHOD_NOT_ALLOWED);
    }
}
