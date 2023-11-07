package lab11_exercise2;

public class Supplier {
	private String name, phone;
	private String regEx = "0[0-9]{6,10}$";

	public String getName() {
		return name;
	}
	public String getPhone() {
		return phone;
	}

	public Supplier(String name, String phone) {
		super();
		if (!phone.matches(regEx)) {
			throw new IllegalArgumentException();
		}
		this.name = name;
		this.phone = phone;
	}
}
