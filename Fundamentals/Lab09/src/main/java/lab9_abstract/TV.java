package lab9_abstract;

public class TV implements IItem {

	private static final double SALE_PERCENTAGE = 0.80;

	private double price;
	private int size;

	public TV(double price, int size) {
		super();
		this.price = price;
		this.size = size;
	}

	public double calculateSalePrice() {
		return this.price * SALE_PERCENTAGE;
	}

	@Override
	public String getName() {
		return "TV (" + size + "\")";
	}

	@Override
	public double getPrice() {
		return this.price;
	}

	@Override
	public void setPrice(double price) {
		this.price = price;
	}

	public int getSize() {
		return size;
	}
}
