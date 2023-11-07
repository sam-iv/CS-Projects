package lab3;

public class User {
	private String name;
	private Email email;
	
	public String getName() {
		return name;
	}

	public User(String fullName, Email emailAddress) {
		super();
		this.name = fullName;
		this.email = emailAddress;
	}

	@Override
	public String toString() {
		return this.name + " <" + this.email.getEmailAddress() + ">";
	}
}
