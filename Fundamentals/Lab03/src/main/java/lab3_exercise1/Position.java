package lab3_exercise1;

public class Position {
	private String role;

	public String getRoleName() {
		return role;
	}
	public void setRoleName(String role) {
		this.role = role;
	}
	
	public Position() {
		super();
		this.role = null;
	}
}
