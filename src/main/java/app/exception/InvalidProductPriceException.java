package app.exception;

/**
 * 
 * @author ganesh
 *
 */
public class InvalidProductPriceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidProductPriceException() {
		this("Invalid product price");
	}
	
    public InvalidProductPriceException(String msg) {
		super(msg);
	}
}
