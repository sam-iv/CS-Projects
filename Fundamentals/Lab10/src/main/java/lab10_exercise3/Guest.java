package lab10_exercise3;

import java.util.ArrayList;
import java.util.List;

public class Guest implements IGuest {

	private String forename, surname, address, telephone;
	private List<Charge> charges;

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

	public Guest(String forename, String surname, String address,
			String telephone) {
		super();
		this.forename = forename;
		this.surname = surname;
		this.address = address;
		this.telephone = telephone;
		charges = new ArrayList<>();
	}

	@Override
	public void addCharge(Service service, double charge) {
		Charge newCharge = new Charge(service, charge);
		charges.add(newCharge);
	}

	@Override
	public double calculateTotalChargeWithoutVAT() {
		// TODO Auto-generated method stub
		double totalCharge = 0D;
		for (Charge charge : charges) {
			totalCharge += charge.getCharge();
		}
		return totalCharge;
	}

	@Override
	public double calculateVATChargeAtRate(VATRate rate) {
		// TODO Auto-generated method stub
		if (rate == VATRate.ZERO) {
			return 0D;
		}

		double totalVAT = 0D;
		for (Charge charge : charges) {
			totalVAT += (charge.getCharge() * rate.getPercentage());
		}
		return totalVAT;
	}

	@Override
	public String toString() {
		return forename + " " + surname + ", " + address + ", " + telephone;
	}

}
