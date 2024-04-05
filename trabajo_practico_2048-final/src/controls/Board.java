package controls;

/**
 * 
 */
public class Board {
	private int[][] board;
	private int size;

	public Board(int n) {
		this.board = new int[n][n];
		this.size = n;
	}

	public int getElement(int x, int y) {
		return this.board[x][y];
	}

	public void setElement(int x, int y, int value) {
		this.board[x][y] = value;
	}

	public int setDouble(int x, int y) {
		this.board[x][y] *= 2;
		return this.board[x][y];
	}

	public void setZero(int x, int y) {
		this.board[x][y] = 0;
	}
	
	public boolean isZero(int x, int y) {
		return (board[x][y] == 0) ? true : false;
	}
	
	public int getSize() {
		return this.size;
	}
}
