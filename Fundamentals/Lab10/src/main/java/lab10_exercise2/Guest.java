package lab10_exercise2;

import java.util.ArrayList;
import java.util.List;

public class Guest implements IGuest {

	private String forename, surname, address, telephone;
	private List<Charge> charges;

	public Guest(String forename, String surname, String address,
			String telephone) {
		super();
		this.forename = forename;
		this.surname = surname;
		this.address = address;
		this.telephone = telephone;
		charges = new ArrayList<>();
	}

	public String getForename() {
		return forename;
	}
	public String getSurname() {
		return surname;
	}
	public String getAddress() {
		return address;
	}
	public String getTelephone() {
		return telephone;
	}
	public List<Charge> getCharges() {
		return charges;
	}

	@Override
	public void addCharge(Service service, double charge) {
		Charge newCharge = new Charge(service, charge);
		charges.add(newCharge);
	}

	@Override
	public double calculateTotalChargeWithoutVAT() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double calculateVATChargeAtRate(VATRate rate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() { // Outputs to console
		return forename + " " + surname + ", " + address + ", " + telephone;
	}

}
