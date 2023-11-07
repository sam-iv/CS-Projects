package exercise3;

public class Assessment {
	private String name;
	private int maximum;
	private double mark;
	
	public String getName() {
		return name;
	}
	public int getMaximum() {
		return maximum;
	}
	public double getMark() {
		return mark;
	}
	
	public Assessment(String name, double mark) {
		super();
		this.name = name;
		this.maximum = 100;
		this.mark = mark;
	}
}
