package lab5_exercise3;

public class Customer {
	private String name, surname;

	public char getName() {
		
		return name.charAt(0);
	}

	public String getSurname() {
		return surname;
	}
	
	public Customer(String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "" + getName() + ". " + surname;
	}
	
	
}
