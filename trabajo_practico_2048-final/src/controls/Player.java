package controls;

public class Player implements Comparable<Player> {

	private int score;
	private String name;

	public Player(int score, String name) {
		this.score = score;
		this.name = name;
	}

	@Override
	public String toString() {
		return score + " " + name;

	}

	public String getPlayer() {
		return score + "," + name;
	}
	
	public int getScore() {
		return score;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public int compareTo(Player play) {
		return -(score - play.getScore());
	}
}
