package lab4_exercise3;

public class WeekTimetable {
	private Day[] days;
	
	public WeekTimetable() {
		super();
		days = new Day[5];
	}
	
	public void addDailySessions(int index, Day day) {
		days[index] = day;
	}
	
	public String printTimetable() {
		String output = "";
		for (Day day : days) {
			output += day.getName() + "\n" + day.toString() + "\n";
		}
		
		return output;
	}
}
