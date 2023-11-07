package exercise3;

public class Module {
	private Assessment assessment1, assessment2;
	
	public Module(Assessment assessment1, Assessment assessment2) {
		super();
		this.assessment1 = assessment1;
		this.assessment2 = assessment2;
	}
	
	public double calculateAverage() {
		double avg1 = assessment1.getMark();
		avg1 = (avg1/100) * 20;
		
		double avg2 = assessment2.getMark();
		avg2 = (avg2/100) * 80;
		
		return (avg1 + avg2);
	}

	@Override
	public String toString() {
		return "COM1027 Average = " + calculateAverage() + "% " + "[Labs(20%) = " + assessment1.getMark() + "% ,Coursework(80%) = " 
	+ assessment2.getMark() +"% ]";
	}
}
