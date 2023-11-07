package lab6_exercise3;

public enum Countries {
	CANADA(1, false), CHINA(2, false), RUSSIA(3, false), BRITAIN(4, false), FRANCE(5, true);
	
	private int index;
	private boolean eu; //where true means the country is in the European Union
	
	private Countries(int index, boolean eu) {
		this.index = index;
		this.eu = eu;
	}
	
	public int getIndex() {
		return index;
	}
	
	public boolean isEU() {
		if (eu) {
			return true;
		} else {
			return false;
		}
	}
	
	public static Countries[] orderedCountries() {
		Countries[] output = new Countries[Countries.values().length]; //Creates an empty array with the size of how many countries are enumerated
		
		for (Countries country : Countries.values()) { //foreach loop for each country in the enum
			output[country.getIndex()] = country; //gives each country in the enum an index
		}
		return output;
	}
}
