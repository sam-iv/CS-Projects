/**
 * 
 */
package lab2;

/**
 * @author samiv
 *
 */
public class NumberTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		NumberInfo number = new NumberInfo(3);
		System.out.println(number.getValue());
		
		number.setValue(4);
		System.out.println(number.getValue());
		
		System.out.println(number.getValue());
		number.Increment();
		System.out.println(number.getValue());
		 */
		
		NumberInfo number = new NumberInfo(3);
		number.increment();
		System.out.println(number.getValue());
		number.increment();
		System.out.println(number.getValue());
		number.increment();
		System.out.println(number.getValue());
	}

}
