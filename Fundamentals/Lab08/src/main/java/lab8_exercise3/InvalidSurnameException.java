package lab8_exercise3;

public class InvalidSurnameException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6871978051115433827L;

	public InvalidSurnameException() {
		super("Invalid Surname");
	}

	public InvalidSurnameException(String message) {
		super(message);
	}

}
