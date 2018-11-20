package exception;

/**
 * 
 * @author ganesh
 *
 */
public class InvalidOrderQuantityException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InvalidOrderQuantityException() {
		this("Invalid product order quantity");
	}
	
    public InvalidOrderQuantityException(String msg) {
		super(msg);
	}
}
