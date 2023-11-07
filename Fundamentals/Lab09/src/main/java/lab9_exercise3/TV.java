package lab9_exercise3;

public class TV extends AbstractItem {

	private static final double SALE_PERCENTAGE = 0.80;

	private int size;

	public TV(double price, int size) {
		super(price);
		this.size = size;
	}

	public double calculateSalePrice() {
		return getPrice() * SALE_PERCENTAGE;
	}

	@Override
	public String getName() {
		return "TV (" + size + "\")";
	}

	public int getSize() {
		return size;
	}
}
