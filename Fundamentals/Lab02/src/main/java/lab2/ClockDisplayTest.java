/**
 * 
 */
package lab2;

/**
 * @author samiv
 *
 */
public class ClockDisplayTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClockDisplay clock = new ClockDisplay();
		
		for (int i = 0; i < 100; i++) {
			clock.timeTick();
			System.out.println(clock.toString());
		}
	}

}
