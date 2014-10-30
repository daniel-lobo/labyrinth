package exceptions;

public class ParsingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ParsingException() {
		super("Corrupted file. Choose another one");
	}
}