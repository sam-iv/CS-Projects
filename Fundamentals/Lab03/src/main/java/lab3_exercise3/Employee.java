package lab3_exercise3;

public class Employee {
	private int id;
	private String forename, surname;
	private AnnualSalary salary;
	private Position companyPosition;
	
	public int getId() {
		return id;
	}
	public String getForename() {
		return forename;
	}
	public String getSurname() {
		return surname;
	}
	public double getSalary() {
		return salary.getSalary();
	}
	public String getPositionName() {
		return companyPosition.getRoleName();
	}

	public Employee(int id, String forename, String surname, AnnualSalary salary, Position companyPosition) {
		super();
		this.id = id;
		this.forename = forename;
		this.surname = surname;
		this.salary = salary;
		this.companyPosition = companyPosition;
	}
	
	public void displayEmployeeName() {
		System.out.println(forename + " " + surname);
	}
	
	private boolean eligibleForBonus() {
		if (salary.getSalary() >= 40000) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public String toString() {
		if (eligibleForBonus() == true) {
			return surname + ", " + forename + " (" + id + "): " + getPositionName() + " at £" + getSalary() + 
					" (£" + salary.calculateTax() + " tax) and is eligible for bonus.";
		} else {
			return surname + ", " + forename + " (" + id + "): " + getPositionName() + " at £" + getSalary() + 
					" (£" + salary.calculateTax() + " tax) and is not eligible for bonus.";
		}
	}
}
