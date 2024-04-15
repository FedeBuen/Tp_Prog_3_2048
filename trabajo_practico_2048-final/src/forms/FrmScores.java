package forms;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
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
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
		setTitle("Mejores Puntajes");
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		/**
		 * Tabla con los puntajes
		 */
		tblScores = new JTable();
		contentPane.add(tblScores);
		tblScores.setFont(new Font("Kristen ITC", Font.BOLD, 16)); 
		tblScores.setForeground(new Color(0, 196, 156));
		tblScores.setBackground(new Color( 191, 201, 202));
		tblScores.setShowGrid(false);
		tblScores.setRowHeight(30); 
		
		Color customColor = new Color(46, 27, 91); // RGB para #1E252D
		tblScores.setBackground(customColor);
		tblScores.setBounds(90, 200, 600, 170);
		
		JLabel lblNewLabel = new JLabel("  Posición        Puntaje               Jugador           Maximo");
		lblNewLabel.setForeground(new Color(0, 196, 156));
		lblNewLabel.setFont(new Font("Kristen ITC", Font.BOLD, 16));
		lblNewLabel.setBounds(90, 90, 600, 170);
		contentPane.add(lblNewLabel);
		FileScoresManager fsm = new FileScoresManager();
		
		/**
		 * Boton de borrar puntajes
		 */
		JButton btnBorrarPuntajes = new JButton("Borrar Puntajes");
		btnBorrarPuntajes.setFont(new Font("Kristen ITC", Font.PLAIN, 14));
		btnBorrarPuntajes.setFocusable(false);
		btnBorrarPuntajes.setFocusTraversalKeysEnabled(false);
		btnBorrarPuntajes.setFocusPainted(false);
		btnBorrarPuntajes.setBorder(null);
		btnBorrarPuntajes.setForeground(new Color(18, 2, 43));
		btnBorrarPuntajes.setBackground(new Color(239, 3, 65));
		btnBorrarPuntajes.setFocusable(false);
		
		btnBorrarPuntajes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				 int option = JOptionPane.showConfirmDialog(null, "¿Desea reestablecer los puntajes?", "Confirmar", JOptionPane.OK_CANCEL_OPTION);
			        if (option == JOptionPane.OK_OPTION) {
						fsm.deleteScores();
						JOptionPane.showMessageDialog(null, 
								"Los puntajes fueron reestablecidos", "Importante", JOptionPane.INFORMATION_MESSAGE);
						setVisible(false);
			        }
			}
		});
		btnBorrarPuntajes.setBounds(300, 400, 173, 25);
		contentPane.add(btnBorrarPuntajes);
	
		listPlayers = fsm.getPlayers();
		loadElement();
		
	}
	
	private void loadElement() {
		model = (DefaultTableModel) tblScores.getModel();
		model.setColumnCount(4);
		model.setRowCount(5);
		
		int i = 0;
		int pos = 1;
		for (Player p : listPlayers) {
			tblScores.setValueAt("   " + pos, i, 0);
			tblScores.setValueAt(p.getScore(), i, 1);
			tblScores.setValueAt(p.getName(), i, 2);
			tblScores.setValueAt(p.getPoints(), i, 3);
			i++;
			pos++;
		}
		
	}
}
