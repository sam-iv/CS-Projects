package exercise1;

import java.text.DecimalFormat;

public class ClockDisplay {
	
	private NumberInfo hours, minutes;
	private boolean clockType; //When clockType is true, 12 hour, when clockType is false 24.
	
	public ClockDisplay(boolean clockType) {
		super();
		this.hours = new NumberInfo(24); //
		this.minutes = new NumberInfo(60);
		this.clockType = clockType;
	}
	
	public void timeTick() {
		minutes.Increment();
		
		if (minutes.getValue() == 0) {
			hours.Increment();
		}
	}

	@Override
	public String toString() {
		DecimalFormat style1 = new DecimalFormat("00");
		DecimalFormat style2 = new DecimalFormat("0");
		
		if (clockType == true) {
			if (hours.getValue() > 12) {
				return (style2.format(this.hours.getValue() - 12) + ":" + style1.format(this.minutes.getValue()) + "pm");
			}
			if (hours.getValue() == 12) {
				return (style2.format(this.hours.getValue()) + ":" + style1.format(this.minutes.getValue()) + "pm");
			}
			else {
				return (style2.format(this.hours.getValue()) + ":" + style1.format(this.minutes.getValue()) + "am");
			}
		}
		if (clockType == false) {
			return (style1.format(this.hours.getValue()) + ":" + style1.format(this.minutes.getValue()));
		}
		return null;
	}
}
