package lab4_exercise3;

public class Session {
	
	private String sessionName;
	private int startTime, endTime;
	
	public String getSessionName() {
		return sessionName;
	}
	public int getStartTime() {
		return startTime;
	}
	public int getEndTime() {
		return endTime;
	}
	
	
	/*
	public String getSessionName() {
		return sessionName;
	}
	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public int getEndTime() {
		return endTime;
	}
	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}
	*/
	
	public Session(String sessionName, int startTime, int endTime) {
		super();
		this.sessionName = sessionName;
		this.startTime = startTime;
		this.endTime = endTime;
	}
}
