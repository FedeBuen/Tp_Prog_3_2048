package forms;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controls.FileScoresManager;
import controls.Player;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmScores extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblScores;
	private DefaultTableModel model;
	private List<Player> listPlayers;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmScores frame = new FrmScores();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmScores() {
		setResizable(false);
		setTitle("Mejores Puntajes");
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tblScores = new JTable();
		tblScores.setBounds(12, 46, 424, 170);
		contentPane.add(tblScores);
		tblScores.setFont(new Font("Dialog", Font.PLAIN, 24)); 
		tblScores.setForeground(Color.darkGray);
		tblScores.setBackground(new Color( 191, 201, 202));
		tblScores.setShowGrid(false);
		tblScores.setRowHeight(30); 
		
		JLabel lblNewLabel = new JLabel("  Posición                   Puntaje                      Jugador");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel.setBounds(12, 12, 416, 22);
		contentPane.add(lblNewLabel);
		FileScoresManager fsm = new FileScoresManager();
		
		JButton btnBorrarPuntajes = new JButton("Borrar Puntajes");
		btnBorrarPuntajes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fsm.deleteScores();
				JOptionPane.showMessageDialog(null, 
						"Los puntajes fueron reestablecidos", "Importante", JOptionPane.INFORMATION_MESSAGE);
				setVisible(false);
			}
		});
		btnBorrarPuntajes.setBounds(132, 228, 173, 25);
		contentPane.add(btnBorrarPuntajes);

		listPlayers = fsm.getPlayers();
		loadElement();
		
	}
	
	
	
	private void loadElement() {
		model = (DefaultTableModel) tblScores.getModel();
		model.setColumnCount(3);
		model.setRowCount(5);
		
		int i = 0;
		int pos = 1;
		for (Player p : listPlayers) {
			tblScores.setValueAt("   " + pos, i, 0);
			tblScores.setValueAt(p.getScore(), i, 1);
			tblScores.setValueAt(p.getName(), i, 2);
			i++;
			pos++;
		}
		
	}
}
