package forms;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import controls.Board;
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

	/**
	 * Create the frame.
	 */
	public FrmBoard(String modeColor, int size) {
		int numberWin = 2048;
		setResizable(false);
		sizeBoard = size;
		cp = new ColorPalette(modeColor);
		setTitle("Juego 2048");
		setBounds(100, 100, 450, 510);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblScoreTitle = new JLabel("Puntaje:");
		lblScoreTitle.setFont(new Font("Dialog", Font.BOLD, 18));
		lblScoreTitle.setBounds(12, 12, 87, 15);
		contentPane.add(lblScoreTitle);

		JLabel lblScore = new JLabel("0");
		lblScore.setFont(new Font("Dialog", Font.BOLD, 18));
		lblScore.setBounds(111, 12, 70, 15);
		contentPane.add(lblScore);

		tblBoard = new JTable();

		tblBoard.setRowMargin(10);
		tblBoard.setRowHeight(400 / size);
		tblBoard.setCellSelectionEnabled(false); // Deshabilitar la selección de celdas
		tblBoard.setRowSelectionAllowed(false); // Deshabilitar la selección de filas
		tblBoard.setColumnSelectionAllowed(false); // Deshabilitar la selección de columnas
		tblBoard.setRequestFocusEnabled(false);
		tblBoard.setShowGrid(false);
		tblBoard.setBackground(Color.GRAY);
		tblBoard.setBounds(12, 39, 424, 407);
		contentPane.add(tblBoard);

		tblBoard.setDefaultRenderer(Object.class, new TableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				JLabel label = new JLabel();
				label.setOpaque(true);
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setVerticalAlignment(SwingConstants.CENTER);

				label.setFont(new Font("Ebrima", Font.BOLD, 32));
				label.setForeground(Color.DARK_GRAY); // Establecer el color del texto

				String number = value != null ? value.toString() : ""; // Manejar el valor nulo
				Color backgroundColor = cp.getColor(number);
				label.setBackground(backgroundColor);
				label.setText(number);

				Border border = BorderFactory.createLineBorder(Color.GRAY, 7); // Borde negro de 2 píxeles
				label.setBorder(border);

				return label;
			}
		});


		tblBoard.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				game.play(KeyEvent.getKeyText(keyCode).toUpperCase());
				lblScore.setText(String.valueOf(game.getScore()));
				if (game.getWin() >= numberWin) {
					loadElement();
					tblBoard.setEnabled(false);
					JOptionPane.showMessageDialog(null, "Usted Gana !!!", "Felicitaciones",
							JOptionPane.INFORMATION_MESSAGE);
					saveScore();
				} else if (game.getWin() == -1) {
					loadElement();
					tblBoard.setEnabled(false);
					JOptionPane.showMessageDialog(null, "No logro el objetivo !!!", "Game Over",
							JOptionPane.ERROR_MESSAGE);
					saveScore();
				}
				loadElement();
			}
		});
		
		JButton btnNew = new JButton("Nuevo Juego");
		btnNew.setFocusable(false);
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblScore.setText("0");
				btnNew.setFocusable(false);
				tblBoard.setFocusable(true);
				tblBoard.setEnabled(true);
				initializeGame();
				loadElement();
			}
		});
		btnNew.setBounds(298, 8, 130, 25);
		contentPane.add(btnNew);
		
		initializeGame();
		loadElement();
		
	}

	private void initializeGame() {
		Board board = new Board(sizeBoard);
		game = new Game(board);
		this.board = game.initialize();

	}

	private void loadElement() {
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
				Player player = new Player(game.getScore(), name);
				FileScoresManager fsm = new FileScoresManager();
				fsm.addPlayer(player);				
			}
		}
	}
}
