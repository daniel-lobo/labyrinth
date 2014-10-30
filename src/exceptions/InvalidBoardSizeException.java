package exceptions;

public class InvalidBoardSizeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidBoardSizeException() {
		super("Trying to access restricted area");
	}
}