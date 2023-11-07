package lab4_exercise1;

public class Neighbourhood {
	
	private House[] houses = new House[3];
	
	public House getHouses(int index) {
		return houses[index];
	}
	
	public void setHouses(int index, int numberOfHouse, int numberBottles) {
		houses[index] = new House(numberOfHouse, numberBottles);
	}
	
	public Neighbourhood() {
		super();
		houses[0] = new House(3, 4);
		houses[1] = new House(5, 1);
		houses[2] = new House(7, 2);
	}
	
	public int calculateTotal() {
		int total = 0;
		
		for (House house : houses) {
			total += house.getNumberBottles();
		}
		return total;
	}
	
	public String displayArray() {
		String message = "";
		
		for (House house : houses) {
			message += "Number of bottles in House number " + house.getNumberOfHouse()
			+ " is " + house.getNumberBottles() + "\n";
		}
		
		message += "Total number of bottles to be delivered: " + calculateTotal();
		
		return message;
	}
}
