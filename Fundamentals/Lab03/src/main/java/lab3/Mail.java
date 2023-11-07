package lab3;

public class Mail {
	private User sender, recipient;
	private String message;
	
	public String getSender() {
		return sender.toString();
	}
	public User getRecipient() {
		return recipient;
	}
	public String getMessage() {
		return message;
	}
	
	public Mail(User sender, User recipient, String message) {
		super();
		this.sender = sender;
		this.recipient = recipient;
		this.message = message;
	}
	
	private void isEmpty() {
		if (this.getMessage() == null || this.getMessage() == "") {
			message = "** Something went wrong! **";
		} else {
		}
	}
	
	public void print() {
		this.isEmpty();
		System.out.println("From: " + sender);
		System.out.println("To: " + this.recipient);
		System.out.println("Message: " + this.message);
	}
}
