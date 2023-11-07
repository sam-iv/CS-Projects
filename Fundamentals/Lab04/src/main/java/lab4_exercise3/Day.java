package lab4_exercise3;

import java.util.Arrays;

public class Day {
	private String name;
	private Session[] sessions;
	
	
	public String getName() {
		return name;
	}
	public void setSession(int index, String moduleName, int startTime, int endTime) {
		sessions[index] = new Session(moduleName, startTime, endTime);
	}
	
	public Day(String name) {
		super();
		this.name = name;
		this.sessions = new Session[4];
	}

	@Override
	public String toString() {
		String output = "";
		for (Session session : sessions) {
			if (session == null) {
				break;
			}
			output += session.getSessionName() + ": " + session.getStartTime() + " - " + session.getEndTime() + "\n";
		}
		
		return output;
	}
	
	public void display() {
		System.out.println(name + "\n" + toString());
	}
}
