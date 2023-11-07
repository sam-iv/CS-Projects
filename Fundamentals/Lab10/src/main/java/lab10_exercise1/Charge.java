package lab10_exercise1;

public class Charge {
	private Service service;
	private double charge;

	public Service getService() {
		return service;
	}
	public double getCharge() {
		return charge;
	}

	public Charge(Service service, double charge) {
		super();
		this.service = service;
		this.charge = charge;
	}

	public double calculateVAT() {
		return (charge * service.getRate().getPercentage());
	}
}
