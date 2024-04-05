package forms;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import java.awt.Color;

public class FrmInit {

	private JFrame frmInit;
	private final ButtonGroup buttonGroupColor = new ButtonGroup();
	private final ButtonGroup buttonGroupSize = new ButtonGroup();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmInit window = new FrmInit();
					window.frmInit.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrmInit() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmInit = new JFrame();
		frmInit.setResizable(false);
		frmInit.setTitle("Juego 2048");
		frmInit.setBounds(100, 100, 469, 377);
		frmInit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInit.setLocationRelativeTo(null);
		frmInit.getContentPane().setLayout(null);
		//******* NO CARGA EL ICONO ??
		//ImageIcon icon = new ImageIcon("images/2048.svg");
		frmInit.setIconImage(new ImageIcon("images/2048.svg").getImage());
		//*******
        JPanel panel = new JPanel() {
            private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage image = ImageIO.read(new File("images/background.jpeg")); 
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        
        frmInit.setContentPane(panel);
		
		JRadioButton jrbPastel = new JRadioButton("PASTEL");
		jrbPastel.setForeground(new Color(0, 0, 0));
		jrbPastel.setSelected(true);
		buttonGroupColor.add(jrbPastel);
		jrbPastel.setBounds(80, 248, 149, 23);
		panel.add(jrbPastel);
		
		JRadioButton jrbGreen = new JRadioButton("GREEN");
		jrbGreen.setForeground(new Color(0, 0, 0));
		buttonGroupColor.add(jrbGreen);
		jrbGreen.setBounds(228, 248, 149, 23);
		panel.add(jrbGreen);
        
		JRadioButton jrb4 = new JRadioButton("Clasic 4x4");
		jrb4.setForeground(new Color(0, 0, 0));
		jrb4.setSelected(true);
		buttonGroupSize.add(jrb4);
		jrb4.setBounds(80, 305, 149, 23);
		panel.add(jrb4);
		
		JRadioButton jrb5 = new JRadioButton("Large 5x5");
		jrb5.setForeground(new Color(0, 0, 0));
		buttonGroupSize.add(jrb5);
		jrb5.setBounds(228, 305, 149, 23);
		panel.add(jrb5);
		
        JButton btnNuevoJuego = new JButton("Nuevo Juego");
		btnNuevoJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String modeColor = "";
				int size = 4;
				if (jrbPastel.isSelected()) {
					modeColor = jrbPastel.getText();
				} 
				if (jrbGreen.isSelected()) {
					modeColor = jrbGreen.getText();
				}
				if (jrb4.isSelected()) {
					size = 4;
				}
				if (jrb5.isSelected()) {
					size = 5;
				}
				
				FrmBoard fb = new FrmBoard(modeColor, size);
				fb.setVisible(true);

			}
		});
		panel.setLayout(null);
		btnNuevoJuego.setBounds(45, 29, 180, 25);
		frmInit.getContentPane().add(btnNuevoJuego);
		
		JButton btnMejoresPuntajes = new JButton("Mejores Puntajes");
		btnMejoresPuntajes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmScores fs = new FrmScores();
				fs.setVisible(true);
			}
		});
		btnMejoresPuntajes.setBounds(234, 29, 180, 25);
		frmInit.getContentPane().add(btnMejoresPuntajes);
		
		JLabel lblEscogerLaPaleta = new JLabel("Escoger la paleta de colores del tablero");
		lblEscogerLaPaleta.setForeground(new Color(115, 210, 22));
		lblEscogerLaPaleta.setBounds(70, 225, 333, 15);
		panel.add(lblEscogerLaPaleta);
		
		JLabel lblEscogeElTamao = new JLabel("Escoge el tamaño del tablero");
		lblEscogeElTamao.setForeground(new Color(115, 210, 22));
		lblEscogeElTamao.setBounds(70, 279, 333, 15);
		panel.add(lblEscogeElTamao);
		
	}
}
