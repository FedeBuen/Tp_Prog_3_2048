package controls;

public class Player implements Comparable<Player> {

	private int score;
	private String name;
	private int points;

	public Player(int score, String name, int points) {
		this.score = score;
		this.name = name;
		this.points = points;
	}

	@Override
	public String toString() {
		return score + " " + name + " " + points;

	}

	public String getPlayer() {
		return score + "," + name + "," + points;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getPoints() {
		return this.points;
	}
	
	@Override
	public int compareTo(Player play) {
		return -(score - play.getScore());
	}
}
