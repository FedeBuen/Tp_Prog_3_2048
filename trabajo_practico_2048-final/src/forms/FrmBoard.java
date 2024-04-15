package forms;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import controls.Board;
import forms.CustomDialog;
import controls.FileScoresManager;
import controls.Game;
import controls.Player;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmBoard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblBoard;
	private DefaultTableModel model;
	private int sizeBoard;
	private Game game;
	private Board board;
	private ColorPalette cp;
	private KeyListener keyListener;
	private JLabel lblClue;

	/**
	 * Create the frame.
	 */
	public FrmBoard(String modeColor, int size) {

		contentPane = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				try {
					BufferedImage image = ImageIO.read(new File("images/background_game.jpg"));
					g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};

		setResizable(false);
		sizeBoard = size;
		cp = new ColorPalette(modeColor);
		setTitle("Juego 2048");
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblScoreTitle = new JLabel("Puntos: ");
		lblScoreTitle.setForeground(new Color(0, 196, 156));
		lblScoreTitle.setFont(new Font("Kristen ITC", Font.BOLD, 20));
		lblScoreTitle.setBounds(24, 51, 103, 23);
		contentPane.add(lblScoreTitle);

		JLabel lblScore = new JLabel("0");
		lblScore.setForeground(new Color(0, 196, 156));
		lblScore.setFont(new Font("Kristen ITC", Font.BOLD, 20));
		lblScore.setBounds(123, 51, 78, 23);
		contentPane.add(lblScore);

		tblBoard = new JTable();

		tblBoard.setRowHeight(400 / size);
		tblBoard.setCellSelectionEnabled(false); // Deshabilitar la selección de celdas
		tblBoard.setRowSelectionAllowed(false); // Deshabilitar la selección de filas
		tblBoard.setColumnSelectionAllowed(false); // Deshabilitar la selección de columnas
		tblBoard.setRequestFocusEnabled(false);
		tblBoard.setShowGrid(false);

		Color customColor = new Color(46, 27, 91); // RGB para #1E252D
		tblBoard.setBackground(customColor);
		tblBoard.setBounds(180, 88, 424, 407);

		contentPane.add(tblBoard);

		tblBoard.setDefaultRenderer(Object.class, new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				JLabel label = new JLabel();
				label.setOpaque(true);
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setVerticalAlignment(SwingConstants.CENTER);

				label.setFont(new Font("Kristen ITC", Font.BOLD, 24));
				label.setForeground(customColor); // Establecer el color del texto

				String number = value != null ? value.toString() : ""; // Manejar el valor nulo
				Color backgroundColor = cp.getColor(number);
				label.setBackground(backgroundColor);
				label.setText(number);

				Border border = BorderFactory.createLineBorder(customColor, 7); // Borde negro de 2 píxeles
				label.setBorder(border);

				return label;
			}
		});

		keyListener = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				String message = "";
				game.play(keyCode);
				lblScore.setText(String.valueOf(game.getScore()));
				if (game.isWin()) {
					message = "Completo el juego con exito!!";
					loadElement();
					 tblBoard.setEnabled(false);
				        CustomDialog winDialog = new CustomDialog("images/winPhoto.jpg");
				        winDialog.setVisible(true);
				} else if (game.isEnd()) {
					loadElement();
			        tblBoard.setEnabled(false);
			        CustomDialog gameOverDialog = new CustomDialog("images/lostPhoto.jpg");
			        gameOverDialog.setVisible(true);
			        saveScore();
				}
				loadElement();
			}
		};

		tblBoard.addKeyListener(keyListener);

		JButton btnNew = new JButton("Nuevo Juego");
		btnNew.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
		btnNew.setFocusable(false);
		btnNew.setFocusTraversalKeysEnabled(false);
		btnNew.setFocusPainted(false);
		btnNew.setBorder(null);
		btnNew.setForeground(new Color(18, 2, 43));
		btnNew.setBackground(new Color(239, 3, 65));
		btnNew.setFocusable(false);

		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int option = JOptionPane.showConfirmDialog(null, "¿Desea volver a jugar?", "Confirmar",
						JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					initializeGame();
					loadElement();
					lblScore.setText("0");
					btnNew.setFocusable(false);
					// Si presiona varias veces solo agrega un keyListener
					if (tblBoard.getKeyListeners().length == 1) {
						tblBoard.addKeyListener(keyListener);
					}
				}

			}
		});
		btnNew.setBounds(200, 519, 173, 25);
		contentPane.add(btnNew);

		JButton btnClue = new JButton("Sugerir movimiento");
		btnClue.setFont(new Font("Kristen ITC", Font.PLAIN, 12));
		btnClue.setFocusable(false);
		btnClue.setFocusTraversalKeysEnabled(false);
		btnClue.setFocusPainted(false);
		btnClue.setBorder(null);
		btnClue.setForeground(new Color(18, 2, 43));
		btnClue.setBackground(new Color(239, 3, 65));
		btnClue.setFocusable(false);

		btnClue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblClue.setText(game.findBestMove());
			}

		});

		btnClue.setBounds(410, 519, 173, 25);
		contentPane.add(btnClue);

		lblClue = new JLabel("mejor jugada");
		lblClue.setForeground(new Color(0, 196, 156));
		lblClue.setFont(new Font("Kristen ITC", Font.BOLD, 16));
		lblClue.setBounds(600, 519, 173, 25);
		contentPane.add(lblClue);
		
		
		initializeGame();
		loadElement();

	}

	private void initializeGame() {
		Board board = new Board(sizeBoard);
		game = new Game(board);
		this.board = game.initialize();
	}

	private void loadElement() {
		lblClue.setText("");
		model = (DefaultTableModel) tblBoard.getModel();
		model.setColumnCount(sizeBoard);
		model.setRowCount(sizeBoard);
		for (int i = 0; i < sizeBoard; i++) {
			for (int j = 0; j < sizeBoard; j++) {
				if (this.board.isZero(i, j)) {
					tblBoard.setValueAt("", i, j);

				} else {
					tblBoard.setValueAt(this.board.getElement(i, j), i, j);
				}
			}
		}
	}

	private void saveScore() {
		if (game.getScore() > game.getMin()) {
			String name = JOptionPane.showInputDialog(null, "Por favor, ingresa tu nombre:", "Ranking",
					JOptionPane.INFORMATION_MESSAGE);
			if (name == null || name.trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "No se ha guardado su ranking!", "Ranking",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				Player player = new Player(game.getScore(), name, game.getMaxNumber());
				FileScoresManager fsm = new FileScoresManager();
				fsm.addPlayer(player);
			}
		}
	}
}
