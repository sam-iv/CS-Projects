package lab11_exercise3;

public interface IItem {

	public void addIngredient(Ingredient ingredient);

	public double getTotalWeight();

	public double getTotalCalories();

	public double getTotalFat(IngredientType type);

	public double getTotalSodium(IngredientType type);

}
