package lab6_exercise2;

public class Halfbacks {
	private Player[] halfBackers;
	
	public String getPlayer(RugbyPosition position) {
		switch(position) {
			case FLYHALF:
				return halfBackers[1].getName();
			case SCRUMHALF:
				return halfBackers[0].getName();
			default:
				return null;
		}
	}
	
	public Halfbacks() {
		super();
		halfBackers = new Player[2];
	}
	
	public void assignHalfback(Player player){
		if (player.getPosition() == RugbyPosition.SCRUMHALF) {
			halfBackers[0] = player;
		}
		if (player.getPosition() == RugbyPosition.FLYHALF) {
			halfBackers[1] = player;
		}
	}
	
	public String displayElements() {
		StringBuffer output = new StringBuffer();
		
		for (int i = 0; i < 2; i++) {
			if (halfBackers[i] == null) {
				output.append(i + " - " + "\n");
				continue;
			}
			output.append(i + " - " + halfBackers[i].getName() + " " + halfBackers[i].getPosition());
			output.append("\n");
		}
		
		return output.toString();
	}
}
