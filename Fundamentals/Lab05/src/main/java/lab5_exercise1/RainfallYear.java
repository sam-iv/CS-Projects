package lab5_exercise1;

public class RainfallYear {
	private int year;
	private double[] rainfallMonths;
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	public RainfallYear(int year) {
		super();
		this.year = year;
		rainfallMonths = new double[12];
	}
	
	public double calculateMeanRainfall() {
		double mean = 0;
		
		for (double rain : rainfallMonths) {
			mean += rain;
		}
		mean /= 12;
		
		return mean;
	}
	
	public double calculateHighestRainfall() {
		double highest = 0D;
		
		for (double rain : rainfallMonths) {
			if (rain > highest) {
				highest = rain;
			}
		}
		
		return highest;
	}
	
	public void enterData(double[] rainfall) {
		for (int i = 0; i < 12; i++) {
			rainfallMonths[i] = rainfall[i];
		}
	}
	
	public double getRainfallMonth(String month) {
		switch(month) {
			case "JANUARY":
				return rainfallMonths[0];
			case "FEBRUARY":
				return rainfallMonths[1];
			case "MARCH":
				return rainfallMonths[2];
			case "APRIL":
				return rainfallMonths[3];
			case "MAY":
				return rainfallMonths[4];
			case "JUNE":
				return rainfallMonths[5];
			case "JULY":
				return rainfallMonths[6];
			case "AUGUST":
				return rainfallMonths[7];
			case "SEPTEMBER":
				return rainfallMonths[8];
			case "OCTOBER":
				return rainfallMonths[9];
			case "NOVEMBER":
				return rainfallMonths[10];
			case "DECEMBER":
				return rainfallMonths[11];
			default:
				return 0D;
		}
	}
}
