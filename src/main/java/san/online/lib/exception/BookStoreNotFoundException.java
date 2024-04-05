package san.online.lib.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class BookStoreNotFoundException extends ApplicationException {

	 public BookStoreNotFoundException(final String code) {
	        super(NOT_FOUND, "No book store found with given CODE {%s}".formatted(code));
	    }
}
