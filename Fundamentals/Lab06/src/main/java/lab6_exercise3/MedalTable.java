package lab6_exercise3;

public class MedalTable {
	private int[][] medals;
	
	public MedalTable() {
		super();
		this.medals = new int[Countries.values().length][Medals.values().length];
	}
	
	public void addMedal(Countries countries, Medals medals) {
		this.medals[countries.getIndex() - 1][medals.getIndex()] += 1;
	}
	
	public int getEUMedalCount() {
		int medalCount = 0;
		for (Countries country : Countries.values()) {
			if (country.isEU()) {
				for (int i = 0; i < 3; i++) {
					medalCount += this.medals[country.getIndex() - 1][i];
				}
			}
		}
		return medalCount;
	}
	
	 public int getMedals(Countries countries, Medals medals) {
		 return this.medals[countries.getIndex() - 1][medals.getIndex()];
	 }
}
