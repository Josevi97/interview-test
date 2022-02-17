package interview.exceptions;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends ApiException {
    public ProductNotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }
}
