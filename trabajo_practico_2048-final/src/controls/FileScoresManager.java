package controls;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class FileScoresManager {

	Scanner scanner;
	private List<Player> listPlayers;
	
	public FileScoresManager() {
		uploadScores();
	}

	private void uploadScores() {
		scanner = null;
		Player player;
		listPlayers = new ArrayList<Player>();
		try {
			scanner = new Scanner(new File("Files/playerScores.csv"));
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				String[] data = line.split(",");
				int score = Integer.parseInt(data[0]);
				String name = data[1];
				player = new Player(score, name);
				listPlayers.add(player);

			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	private void saveScores() {
		Collections.sort(listPlayers);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File("Files/playerScores.csv"));
		} catch (IOException e) {
			System.out.println(e);
		}

		for (int i = 0; i < 5; i++) {
			Player player = listPlayers.get(i);
			pw.println(player.getPlayer());

		}
		pw.close();
	}

	public void deleteScores() {
		Player p = new Player(0, "Player");
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File("Files/playerScores.csv"));
		} catch (IOException e) {
			System.out.println(e);
		}

		for (int i = 0; i < 5; i++) {
			pw.println(p.getPlayer());
		}
		pw.close();
	}
	
	
	public void addPlayer(Player player) {
		listPlayers.add(player);
		saveScores();
	}

	public int getMinScore() {
		return listPlayers.get(4).getScore();
	}
	
	public List<Player> getPlayers() {
		return listPlayers;
	}
	
	/**
	 * SOLO PARA PRUEBAS DE CONSOLA EN DESARROLLO
	 */
	public void printPlayerScores() {
		Collections.sort(listPlayers);
		listPlayers.forEach(System.out::println);
	}

}
