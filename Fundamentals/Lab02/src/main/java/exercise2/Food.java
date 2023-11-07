package exercise2;

public class Food {
	private String name;
	private double amountOfSugar;
	
	public String getName() {
		return name;
	}
	public double getSugarLevel() {
		return amountOfSugar;
	}
	
	public Food(String name, double amountOfSugar) {
		super();
		this.name = name;
		this.amountOfSugar = amountOfSugar;
	}
}
