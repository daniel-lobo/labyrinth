package exceptions;

public class PositionOutOfBoundsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public PositionOutOfBoundsException() {
		super("Trying to access restricted area");
	}
}