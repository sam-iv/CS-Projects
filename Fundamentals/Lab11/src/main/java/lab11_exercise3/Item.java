package lab11_exercise3;

import java.util.ArrayList;
import java.util.List;

public class Item implements IItem {

	private List<Ingredient> ingredients;

	public Item() {
		super();
		ingredients = new ArrayList<>();
	}
	@Override
	public void addIngredient(Ingredient ingredient) {
		// TODO Auto-generated method stub
		ingredients.add(ingredient);
	}

	@Override
	public double getTotalWeight() {
		// TODO Auto-generated method stub
		double totalWeight = 0D;
		for (Ingredient ingredient : ingredients) {
			totalWeight += ingredient.getCookedWeight();
		}
		return totalWeight;
	}

	@Override
	public double getTotalCalories() {
		// TODO Auto-generated method stub
		double totalCalories = 0D;
		for (Ingredient ingredient : ingredients) {
			totalCalories += ingredient.getCalories();
		}
		return totalCalories;
	}

	@Override
	public double getTotalFat(IngredientType type) {
		// TODO Auto-generated method stub
		double totalFat = 0D;
		for (Ingredient ingredient : ingredients) {
			if (ingredient.getType() == type) {
				totalFat += ingredient.getFat();
			}
		}
		return totalFat;
	}

	@Override
	public double getTotalSodium(IngredientType type) {
		// TODO Auto-generated method stub
		double totalSodium = 0D;
		for (Ingredient ingredient : ingredients) {
			if (ingredient.getType() == type) {
				totalSodium += ingredient.getSodium();
			}
		}
		return totalSodium;
	}

}
