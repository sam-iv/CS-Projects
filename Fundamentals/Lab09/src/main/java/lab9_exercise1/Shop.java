package lab9_exercise1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Shop {

	private List<IItem> items;

	public Shop() {
		items = new ArrayList<>();

	}

	public void addItem(IItem item) {
		if (item == null) {
			throw new IllegalArgumentException();
		} else {
			items.add(item);
		}

	}

	public String displayCatalogue() {
		StringBuffer sb = new StringBuffer();
		DecimalFormat style00 = new DecimalFormat(".00");
		sb.append("Shop Catalogue\n" + "\n");
		sb.append("Item\t" + "\tPrice" + "" + "\tSale Price" + "\n");
		for (IItem x : items) {
			sb.append(x.getName() + "\t" + "£" + style00.format(x.getPrice())
					+ "\t" + "£" + style00.format(x.calculateSalePrice())
					+ "\n");
		}

		return sb.toString();

	}

}
