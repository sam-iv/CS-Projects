package lab3_exercise2;

public class AnnualSalary {
	private double salary;
	private static final int allowance = 12500;
	private static final double bRate = 0.2; // v
	private static final double hRate = 0.4; //did this so it made sense when reading
	private static final int taxThreshold = 50000; //redundant
	private static final int maxTax = 7500; //redundant
	
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
			double taxSalary = (salary - allowance);
			taxSalary *= bRate;
			
			return(taxSalary);
		}
		if (salary > taxThreshold) {
			double taxSalary = (salary - taxThreshold);
			taxSalary *= hRate;
			
			return(taxSalary + maxTax);
		} else {
			return 0D;
		}
	}
}
