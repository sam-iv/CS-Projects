package lab11_exercise3;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReadyMeal {
	private String name;
	private ProductType type;
	private Factory factory;
	@SuppressWarnings("unused")
	private Date productionDate;
	private List<IItem> items;

	public String getName() {
		return name;
	}
	public ProductType getType() {
		return type;
	}
	public Factory getFactory() {
		return factory;
	}

	public ReadyMeal(String name, ProductType type, Factory factory) {
		super();
		this.name = name;
		this.type = type;
		this.factory = factory;

		productionDate = new Date();

		items = new ArrayList<>();
	}

	public void addItem(IItem biscuit) {
		items.add(biscuit);
	}

	public String printContent() {
		double totalWeight = 0D;
		for (IItem item : items) {
			totalWeight += item.getTotalWeight();
		}

		double totalCalories = 0D;
		for (IItem item : items) {
			totalCalories += item.getTotalCalories();
		}

		double totalFat = 0D;
		for (IItem item : items) {
			totalFat += item.getTotalFat(IngredientType.MEAT);
		}

		double totalSodium = 0D;
		for (IItem item : items) {
			totalSodium += item.getTotalSodium(IngredientType.VEGETABLE);
		}

		char[] typeCharArray = type.toString().toCharArray();
		for (int i = 1; i < typeCharArray.length; i++) {
			typeCharArray[i] = Character.toLowerCase(typeCharArray[i]);
		}
		String typeString = String.valueOf(typeCharArray);

		return typeString + " " + name + " Ready Meal " + totalWeight + " g\n"
				+ "\n" + "Contents\n" + "Calories: " + totalCalories + "kcal\n"
				+ "Meat (fat): " + totalFat + "g\n" + "Vegetable (sodium): "
				+ totalSodium + "g\n" + "\n" + factory.getName() + "\n"
				+ factory.getNumber() + " " + factory.getStreet() + "\n"
				+ factory.getCity() + "\n" + factory.getCounty() + "\n"
				+ factory.getPostcode() + "\n" + factory.getCountry() + "\n";
	}
}
