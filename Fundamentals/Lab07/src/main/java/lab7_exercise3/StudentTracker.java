package lab7_exercise3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentTracker {
	private List<Student> studentList;
	private Map<Integer, String> moduleList;

	public StudentTracker() {
		super();
		studentList = new ArrayList<>();
		moduleList = new HashMap<Integer, String>();
	}

	public void addStudent(Student student, List<Module> moduleList) {
		for (Module module : moduleList) {
			student.addModuleList(module);
		}

		studentList.add(student);
	}

	private void intiliseMap() {
		for (Student student : studentList) {
			moduleList.put(student.getUrn(), student.printModules());
		}
	}

	public String printStudents() {
		StringBuffer output = new StringBuffer();
		for (Student student : studentList) {
			output.append(student.getName() + "(" + student.getUrn() + ")");
			output.append("\n");
		}

		return output.toString();
	}

	public String printModules(int urn) {
		StringBuffer output = new StringBuffer();
		intiliseMap();

		output.append("URN " + urn + " is enrolled in:" + "\n");
		output.append(moduleList.get(urn));
		output.deleteCharAt(output.length() - 1);
		return output.toString();
	}
}
