package lab10_exercise1;

public enum VATRate {
	ZERO(0), LOW(0.05), STANDARD(0.175);

	private double percentage;

	public double getPercentage() {
		return percentage;
	}

	private VATRate(double percentage) {
		this.percentage = percentage;
	}

}
