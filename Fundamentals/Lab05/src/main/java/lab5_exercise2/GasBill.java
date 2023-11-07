package lab5_exercise2;

public class GasBill {
	private String accountNumber;
	private double amount;
	private Customer customer;
	private String regEx = "\\d{4}-\\d{4}-\\d{4}";
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public String getCustomer() {
		return customer.toString();
	}
	
	public GasBill(String accountNumber, double amount, Customer customer) {
		super();
		if (accountNumber.matches(regEx)) {
			this.accountNumber = accountNumber;
		} else {
			this.accountNumber = "Invalid Account";
		}
		
		this.amount = amount;
		this.customer = customer;
	}
	
	private String displayAmountDue() {
		String output = amount+"0";
		return output;
	}
	
	public boolean checkAccountAccuracy() {
		if (accountNumber == "Invalid Account") {
			return false;
		} else {
			return true;
		}
	}
	
	public String displayAccountDetails() {
		return "Gas Bill" + "\n" + " Account Number:"
		 + getAccountNumber() + "\n" + " Customer:" + customer.toString() + "\n"
		  + " Amount due:" + displayAmountDue();
	}
}
