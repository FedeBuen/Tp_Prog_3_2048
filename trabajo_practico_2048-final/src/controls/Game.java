package controls;

import java.util.List;
import java.util.Scanner;



public class Game {

	private ControllerBoard ctrBoard;
	FileScoresManager fsm = new FileScoresManager();

	public Game(Board board) {
		ctrBoard = new ControllerBoard(board);
	}

	public Board initialize() {
		ctrBoard.initBoard();
		return ctrBoard.getBoard();
		
	}
	
	public List<Player> getScores() {
		return fsm.getPlayers();
	}

	public Board play(String key) {
		boolean moved = false;
		switch (key) {
		case "UP" -> moved = ctrBoard.moveUp();
		case "LEFT" -> moved = ctrBoard.moveLeft();
		case "DOWN" -> moved = ctrBoard.moveDown(true);
		case "RIGHT" -> moved = ctrBoard.moveRight();
		case "ARRIBA" -> moved = ctrBoard.moveUp();
		case "IZQUIERDA" -> moved = ctrBoard.moveLeft();
		case "ABAJO" -> moved = ctrBoard.moveDown(true);
		case "DERECHA" -> moved = ctrBoard.moveRight();
		default -> moved = false;
		}
		if (moved) {
			ctrBoard.addRandomElement();
		}
		moved = false;
		return ctrBoard.getBoard();

	}

	/**
	 * Juego version consola 
	 * ------ UTILIZADO SOLO PARA PRUEBAS DE DESAROLLO ******
	 * 
	 */
	public int play() {
		Scanner scanner = new Scanner(System.in);
		ctrBoard.printBoard();
		boolean moved = false;
		boolean exit = false;
		while (!ctrBoard.boardIsFull() && !exit) {

			System.out.print("Enter direction (W/A/S/D) or exit (X) ");
			String direction = scanner.next().toUpperCase();
			switch (direction) {
			case "W" -> moved = ctrBoard.moveUp();
			case "A" -> moved = ctrBoard.moveLeft();
			case "S" -> moved = ctrBoard.moveDown(true);
			case "D" -> moved = ctrBoard.moveRight();
			case "X" -> exit = true;
			default -> System.out.println("Invalid direction. Please enter W/A/S/D.");

			}
			if (moved) {
				ctrBoard.addRandomElement();
			}
			moved = false;
			ctrBoard.printBoard();
		}
		System.out.println("Game Over");
		scanner.close();
		return ctrBoard.getScore();
	}
	
	public int getScore() {
		return ctrBoard.getScore();
	}
	
	public int getMin() {
		return fsm.getMinScore();
	}
		
	public int getWin() {
		return ctrBoard.getWin();
	}
	
	public void test() {
		int up = ctrBoard.testUp();
		int down = ctrBoard.testDown();
		int left = ctrBoard.testLeft();
		int right = ctrBoard.testRight();
		System.out.println(this.mayor(up,down,left,right));
	}
	
	public boolean perdio() {
		boolean suma = true;
		suma &= ctrBoard.boardIsFull();
		suma &= ctrBoard.testDown() == 0;
		suma &= ctrBoard.testUp() == 0;
		suma &= ctrBoard.testLeft() == 0;
		suma &= ctrBoard.testRight() == 0;
		return suma;
	}
	
	private String mayor(int numUp, int numDown, int numLeft, int numRight) {
		if (numUp>numLeft && numUp>numRight) 
			return "VERTICAL";
		if (numLeft>numUp && numLeft>numDown) 
			return "HORIZONTAL";
		if (numUp + numDown + numLeft + numRight == 0)
		return "NO";
		return "CUALQUIERA";
	}
}
