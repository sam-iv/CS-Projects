package lab8_exercise2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonTracker {
	private List<Person> peopleList;

	public PersonTracker() {
		super();
		peopleList = new ArrayList<>();
	}

	public void readTextFile(String filename) {
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(filename));
			String line = reader.readLine();

			while (line != null) {
				breakLine(line);
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
	}

	private void breakLine(String line) {
		String[] attr = line.split(" ");

		addPerson(attr[0], attr[1], Integer.parseInt(attr[2]));
	}

	private void addPerson(String forename, String surname, int age) {
		Person newPerson = new Person(forename, surname, age);
		peopleList.add(newPerson);
	}

	public String displayList() {
		StringBuffer display = new StringBuffer();

		for (Person person : peopleList) {
			display.append(person.toString() + "\n");
		}

		return display.toString();
	}
}
