package lab7_exercise3;

import java.util.ArrayList;
import java.util.List;

public class Student {
	private String name;
	private int urn;
	private List<Module> moduleList;

	public String getName() {
		return name;
	}

	public int getUrn() {
		return urn;
	}

	public Student(String name, int urn) {
		this.name = name;
		this.urn = urn;
		moduleList = new ArrayList<>();
	}

	public void addModuleList(Module module) {
		if (!moduleList.contains(module)) {
			moduleList.add(module);
		}
	}

	public String printModules() {
		StringBuffer output = new StringBuffer();

		for (Module module : moduleList) {
			output.append(module.getName());
			output.append(", ");
		}

		output.deleteCharAt(output.length() - 2);

		return output.toString();
	}
}
