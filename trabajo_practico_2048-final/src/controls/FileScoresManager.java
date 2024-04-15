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
	String path = "files/playerScores.csv";

	
	/**
	 * Constructor de la clase
	 * sin parametros
	 */
	public FileScoresManager() {
		File csvFile = new File(path);
		if (!csvFile.exists()) {
			try {
				// Crear el archivo si no existe
				csvFile.createNewFile();
				deleteScores(); // Inicializo todos los valores 
				System.out.println("Archivo CSV creado en la ruta especificada.");
			} catch (IOException e) {
				System.err.println("Error al crear el archivo CSV: " + e.getMessage());
			}
		}
		uploadScores();
	}
	
	private void uploadScores() {
		scanner = null;
		Player player;
		listPlayers = new ArrayList<Player>();
		try {
			scanner = new Scanner(new File(path));
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				String[] data = line.split(",");
				int score = Integer.parseInt(data[0]);
				String name = data[1];
				int points = Integer.parseInt(data[2]);
				player = new Player(score, name, points);
				listPlayers.add(player);

			}
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	private void saveScores() {
		Collections.sort(listPlayers);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File(path));
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
		Player p = new Player(0, "Player", 0);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File(path));
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

}
