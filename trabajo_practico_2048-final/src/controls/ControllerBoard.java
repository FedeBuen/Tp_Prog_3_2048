package controls;

import java.util.Random;

/**
 * Clase que controla la funcionalidad del tablero y sus movimientos
 * 
 */
public class ControllerBoard {
	private Board board;
	private int score;
	private int win;
	
	public ControllerBoard(Board board) {
		this.board = board;
		this.score = 0;
	}

	/** 
	 * Inicializa el tablero con dos valores que puedes ser 2 o 4
	 * el algun lugar ramdom
	 */
	public void initBoard() {
		addRandomElement();
		addRandomElement();
	}

	/**
	 * Agrega un elemento (2 o 4) en algun lugar random que se encuentre vacio
	 */
	public void addRandomElement() {
		Random random = new Random();
		int value = (random.nextInt(2) + 1) * 2;
		int row, col;
		do {
			row = random.nextInt(board.getSize());
			col = random.nextInt(board.getSize());
		} while (!board.isZero(row, col));
		board.setElement(row, col, value);
	}

	/**
	 * Imprime por consola el tablero
	 * ----- SOLO SE USA PARA PRUEBAS DE DESARROLLO --------
	 */
	public void printBoard() {
		for (int i = 0; i < board.getSize(); i++) {
			for (int j = 0; j < board.getSize(); j++) {
				System.out.print(board.getElement(i, j) + "  ");
			}
			System.out.println();
		}
		System.out.println("SCORE: " + score);
	}

	/**
	 * Devuelve un objeto tipo Board
	 * @return
	 */
	public Board getBoard() {
		return board;
	}
	
	public int getScore() {
		return score;
	}
	
	/**
	 * Controla que el tablero tenga elementos en cero para poder agregar mas
	 * elementos
	 * 
	 * @return false : si el tablero tiene elementos en cero true : si el tablero ya
	 *         esta completo
	 */
	public boolean boardIsFull() {
		for (int i = 0; i < board.getSize(); i++) {
			for (int j = 0; j < board.getSize(); j++) {
				if (board.isZero(i, j)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Movimientos
	 */
	public boolean moveUp() {
		boolean moved = false;

		for (int col = 0; col < board.getSize(); col++) {
			for (int row = 1; row < board.getSize(); row++) {
				if (!board.isZero(row, col)) { // Si el elemento no es cero
					// Mover el elemento hacia arriba
					int currentRow = row;
					while (currentRow > 0 && board.isZero(currentRow - 1, col)) {
						board.setElement(currentRow - 1, col, board.getElement(currentRow, col));
						board.setZero(currentRow, col);
						currentRow--;
						moved = true;
					}
					// Fusionar elementos si son iguales
					if (currentRow > 0 && board.getElement(currentRow - 1, col) == board.getElement(currentRow, col)) {
						score += board.setDouble(currentRow - 1, col);
						setWin(board.getElement(currentRow - 1, col));
						board.setZero(currentRow, col);
						moved = true;
					}
				}
			}
		}
		
		return moved;
	}

	public boolean moveDown() {
		boolean moved = false;
		for (int col = 0; col < board.getSize(); col++) {
			for (int row = board.getSize() - 2; row >= 0; row--) {
				if (!board.isZero(row, col)) { // Si el elemento no es cero
					// Mover el elemento hacia abajo
					int currentRow = row;
					while (currentRow + 1 < board.getSize() && board.isZero(currentRow + 1, col)) {
						board.setElement(currentRow + 1, col, board.getElement(currentRow, col));
						board.setZero(currentRow, col);
						currentRow++;
						moved = true;
					}
					// Fusionar elementos si son iguales
					if (currentRow + 1 < board.getSize()
							&& board.getElement(currentRow + 1, col) == board.getElement(currentRow, col)) {
						score += board.setDouble(currentRow + 1, col);
						setWin(board.getElement(currentRow + 1, col));
						board.setZero(currentRow, col);
						moved = true;
					}
				}
			}
		}
		return moved;
	}

	public boolean moveRight() {
		boolean moved = false;
		for (int row = 0; row < board.getSize(); row++) {
			for (int col = board.getSize() - 2; col >= 0; col--) {
				if (!board.isZero(row, col)) { // Si el elemento no es cero
					// Mover el elemento a la derecha
					int currentCol = col;
					while (currentCol + 1 < board.getSize() && board.isZero(row, currentCol + 1)) {
						board.setElement(row, currentCol + 1, board.getElement(row, currentCol));
						board.setZero(row, currentCol);
						currentCol++;
						moved = true;
					}
					// Fusionar elementos si son iguales
					if (currentCol + 1 < board.getSize()
							&& board.getElement(row, currentCol + 1) == board.getElement(row, currentCol)) {
						score += board.setDouble(row, currentCol + 1);
						setWin(board.getElement(row, currentCol + 1));
						board.setZero(row, currentCol);
						moved = true;
					}
				}
			}
		}
		return moved;
	}

	public boolean moveLeft() {
		boolean moved = false;
		for (int row = 0; row < board.getSize(); row++) {
			for (int col = 1; col < board.getSize(); col++) {
				if (!board.isZero(row, col)) { // Si el elemento no es cero
					// Mover el elemento a la izquierda
					int currentCol = col;
					while (currentCol - 1 >= 0 && board.getElement(row, currentCol - 1) == 0) {
						board.setElement(row, currentCol - 1, board.getElement(row, currentCol));
						board.setZero(row, currentCol);
						currentCol--;
						moved = true;
					}
					// Fusionar elementos si son iguales
					if (currentCol - 1 >= 0
							&& board.getElement(row, currentCol - 1) == board.getElement(row, currentCol)) {
						score += board.setDouble(row, currentCol - 1);
						setWin(board.getElement(row, currentCol - 1));
						board.setZero(row, currentCol);
						moved = true;
					}
				}
			}
		}
		return moved;
	}
	
	
	public void setWin(int value) {
		if (value > this.win) {
			this.win = value;
		}
	}
	
	public int getWin() {
		return this.win;
	}
}
