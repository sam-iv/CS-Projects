package lab5;

/**
 * A simple class used to demonstrate deficiencies in arrays.
 * 
 * @author Stella Kazamia
 * 
 */
public class Example2 {

	public static void main(String[] args) {

		// Add your code here
		for (int i = 1; i < 7; i++) {
			int k = i;
			String output = "" + i;

			for (int j = 0; j < 3; j++) {
				k += 2; // k = k + 2
				output += " " + (k);
			}
			System.out.println(output);
		}
	}
}
