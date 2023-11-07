package lab2;

import java.text.DecimalFormat;

public class ClockDisplay {
	
	private NumberInfo hours, minutes;
	
	public ClockDisplay() {
		this.hours = new NumberInfo(24);
		this.minutes = new NumberInfo(60);
	}
	
	public void timeTick() {
		minutes.increment();
		
		if (minutes.getValue() == 0) {
			hours.increment();
		}
	}

	@Override
	public String toString() {
		DecimalFormat style = new DecimalFormat("00");
		return (style.format(this.hours.getValue()) + ":" + style.format(this.minutes.getValue()));
	}
}
