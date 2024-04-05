package controls;


/****
 * CLASE SOLO PARA PRUEBAS DE DESAROLLO
 */
public class Main {

  public static void main(final String args[]) {
	  FileScoresManager fsm = new FileScoresManager();
	  
	  fsm.printPlayerScores();
	  Board board = new Board(4);
	  Game game = new Game(board);
	  game.initialize();
	  int score = game.play();
	  String name = "Fede";
	  Player play = new Player(score,name);
	  
	  fsm.addPlayer(play);

	  fsm.printPlayerScores();
  }
}


