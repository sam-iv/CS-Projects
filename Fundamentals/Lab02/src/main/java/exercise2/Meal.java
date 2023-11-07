package exercise2;

public class Meal {
	private Food mainCourse, dessert;
	
	public Meal(Food mainCourse, Food dessert) {
		super();
		this.mainCourse = mainCourse;
		this.dessert = dessert;
	}
	
	public double calculateTotalSugarLevel() {
		return (mainCourse.getSugarLevel() + dessert.getSugarLevel());
	}

	@Override
	public String toString() {
		return "Meal [mainCourse = " + mainCourse.getName() + ", dessert = " + dessert.getName() + "]";
	}
}
