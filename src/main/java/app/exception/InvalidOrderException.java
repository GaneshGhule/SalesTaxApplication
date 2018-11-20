package app.exception;

/**
 * 
 * @author ganesh
 *
 */
public class InvalidOrderException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidOrderException() {
		this("Invalid product order");
	}

	public InvalidOrderException(String msg) {
		super(msg);
	}

}
