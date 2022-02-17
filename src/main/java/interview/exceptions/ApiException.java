package interview.exceptions;

import org.springframework.http.HttpStatus;

public class ApiException extends Exception {
    private HttpStatus code;

    public ApiException(HttpStatus code) {
        this.code = code;
    }

    public HttpStatus getCode() {
        return this.code;
    }
}
