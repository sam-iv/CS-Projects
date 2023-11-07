package lab6_exercise1;

public class Player {
	private String name;
	private RugbyPosition position;
	
	public Player(String name, RugbyPosition position) {
		super();
		this.name = name;
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public RugbyPosition getPosition() {
		return position;
	}
}
