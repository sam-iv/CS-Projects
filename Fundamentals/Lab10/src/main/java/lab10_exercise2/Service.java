package lab10_exercise2;

public class Service {
	private String code, name;
	private VATRate rate;
	private double standardCharge;
	private String regEx = "[A-Z]{4}[1-9]{4}";

	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	public VATRate getRate() {
		return rate;
	}
	public double getStandardCharge() {
		return standardCharge;
	}

	public Service(String code, String name, VATRate rate,
			double standardCharge) {
		super();
		if (code == null) {
			throw new NullPointerException();
		}
		if (!code.matches(regEx)) {
			throw new IllegalArgumentException();
		}
		if (name == null) {
			throw new NullPointerException();
		}
		if (standardCharge < 0) {
			throw new IllegalArgumentException();
		}

		this.code = code;
		this.name = name;
		this.rate = rate;
		this.standardCharge = standardCharge;
	}
}
