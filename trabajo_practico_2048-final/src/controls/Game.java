package controls;

import java.util.List;

public class Game {
	private final int GAME_WIN = 2048;
	private BoardManager boardManager;
	FileScoresManager fsm = new FileScoresManager();
	

	public Game(Board board) {
		boardManager = new BoardManager(board);
	}

	public Board initialize() {
		boardManager.initBoard();
		return boardManager.getBoard();
		
	}
	
	public List<Player> getScores() {
		return fsm.getPlayers();
	}

	public Board play(int key) {
		boolean moved = false;
		switch (key) {
		case 38 -> moved = boardManager.moveUp();
		case 37 -> moved = boardManager.moveLeft();
		case 40 -> moved = boardManager.moveDown();
		case 39 -> moved = boardManager.moveRight();
		default -> moved = false;
		}
		if (moved) {
			boardManager.addRandomElement();
		}
		moved = false;
		return boardManager.getBoard();

	}
		
	public int getScore() {
		return boardManager.getScore();
	}
	
	public int getMin() {
		return fsm.getMinScore();
	}
		
	public boolean isWin() {
		return boardManager.getMaxNumber() >= GAME_WIN;
	}
	
	public int getMaxNumber() {
		return boardManager.getMaxNumber();
	}
	
	public String findBestMove() {
		int up = boardManager.testUp();
		int down = boardManager.testDown();
		int left = boardManager.testLeft();
		int right = boardManager.testRight();
		return mayor(up,down,left,right);
	}
	
	public boolean isEnd() {
		boolean suma = true;
		suma &= boardManager.boardIsFull();
		suma &= boardManager.testDown() == 0;
		suma &= boardManager.testUp() == 0;
		suma &= boardManager.testLeft() == 0;
		suma &= boardManager.testRight() == 0;
		return suma;
	}
	
	private String mayor(int numUp, int numDown, int numLeft, int numRight) {
		if (numUp>numLeft && numUp>numRight) 
			return "VERTICAL";
		if (numLeft>numUp && numLeft>numDown) 
			return "HORIZONTAL";
		if (numUp + numDown + numLeft + numRight == 0)
		return "NINGUNO SUMA";
		return "CUALQUIERA SUMA";
	}
}
