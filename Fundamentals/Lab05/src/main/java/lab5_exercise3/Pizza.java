package lab5_exercise3;

public class Pizza {
	private String[] toppings;
	
	public Pizza() {
		super();
		toppings = new String[0];
	}
	
	public void addToppings(String[] toppings) {
		this.toppings = new String[toppings.length];
		for(int i = 0; i < toppings.length; i++) {
			this.toppings[i] = toppings[i];
		}
	}
	
	public String printToppings() {
		String output = "";
		
		for (String topping : toppings) {
			output += topping + ",";
		}
		return (output.substring(0, output.length() - 1));
	}
	
	public double calculateCost() {
		switch(toppings.length) {
			case 1:
			case 2:
			case 3:
			case 4:
				return 8.99;
			case 5:
				return 9.99;
			case 6:
				return 10.99;
			case 7:
				return 11.99;
			case 8:
				return 12.99;
			case 9:
				return 13.99;
			case 10:
				return 14.99;
			default:
				return 0D;
		}
	}
}
