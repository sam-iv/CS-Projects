package lab8_exercise1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Counting {

	public Counting() {
		super();
	}

	public String readTextFile() {
		int counter = 1;
		BufferedReader reader = null;
		StringBuffer temp = new StringBuffer();

		try {
			reader = new BufferedReader(new FileReader("words.txt"));
			String line = reader.readLine();

			while (line != null) {
				temp.append(displayLine(line, counter) + "\n");
				counter++;
				line = reader.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return temp.toString();
	}

	private String displayLine(String line, int counter) {
		return line + " : " + counter;
	}
}
