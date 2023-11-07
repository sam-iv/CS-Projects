package lab9_exercise3;

import java.util.HashMap;
import java.util.Map;

public class Mall extends Shop {
	private HashMap<Integer, Shop> shops;

	public Mall() {
		super();
		shops = new HashMap<Integer, Shop>();
	}

	public void addShop(int number, Shop shop) {
		if (shop == null) {
			throw new NullPointerException();
		}
		if (number < 0) {
			throw new IllegalArgumentException();
		}
		shops.put(number, shop);
	}

	public String displayAllShops() {
		StringBuffer display = new StringBuffer();

		for (Map.Entry<Integer, Shop> shop : shops.entrySet()) {
			display.append(
					shop.getKey() + " : " + shop.getValue().displayCatalogue());
		}
		return display.toString();
	}
}