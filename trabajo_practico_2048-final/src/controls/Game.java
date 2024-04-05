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
		case "DOWN" -> moved = ctrBoard.moveDown();
		case "RIGHT" -> moved = ctrBoard.moveRight();
		case "ARRIBA" -> moved = ctrBoard.moveUp();
		case "IZQUIERDA" -> moved = ctrBoard.moveLeft();
		case "ABAJO" -> moved = ctrBoard.moveDown();
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
			case "S" -> moved = ctrBoard.moveDown();
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
		if (ctrBoard.boardIsFull()) {
			return -1;
		}
		return ctrBoard.getWin();
	}
}
