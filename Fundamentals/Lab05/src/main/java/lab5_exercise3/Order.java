package lab5_exercise3;

import java.util.ArrayList;
import java.util.List;

public class Order {
	private List<Pizza> pizzas;
	private Customer customer;
	
	public Order(Customer customer) {
		super();
		this.customer = customer;
		pizzas = new ArrayList<>();
	}
	
	public void addPizza(Pizza pizza) {
		pizzas.add(pizza);
	}
	
	public double calculateTotal() {
		double total = 0D;
		
		for (Pizza pizza : pizzas) {
			total += pizza.calculateCost();
		}
		return total;
	}
	
	public String printReceipt() {
		return "Customer: " + customer.toString() + "\n"
				+ "Number of Pizzas: " + pizzas.size() + "\n"
				 + "Total Cost: " + calculateTotal();
	}
}
