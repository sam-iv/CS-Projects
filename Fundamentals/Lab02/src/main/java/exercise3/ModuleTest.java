/**
 * 
 */
package exercise3;

/**
 * @author samiv
 *
 */
public class ModuleTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Assessment assessment1 = new Assessment("Labs", 90.0);
		Assessment assessment2 = new Assessment("Coursework", 72.0);
		
		Module COM1027 = new Module(assessment1, assessment2);
		
		System.out.println(COM1027.toString());
	}

}
