package lab3_exercise3;

public class AnnualSalary {
	private double salary;
	private static final int allowance = 12500;
	private static final double bRate = 0.2;
	private static final double hRate = 0.4;
	private static final int taxThreshold = 50000;
	private static final int maxTax = 7500;
	
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}

	public AnnualSalary() {
		super();
	}
	
	public double calculateTax() {
		if (salary > allowance && salary <= taxThreshold) {
			double tax = (salary - allowance);
			tax *= bRate;
			
			return(tax);
		}
		if (salary > taxThreshold) {
			double tax = (salary - taxThreshold);
			tax *= hRate;
			
			return(tax + maxTax);
		} else {
			return 0D;
		}
	}
}
