package forms;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import java.awt.Color;
import java.awt.Component;

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
		frmInit.setBounds(100, 100, 800, 600);
		frmInit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInit.setLocationRelativeTo(null);
		frmInit.getContentPane().setLayout(null);
       
        JPanel panel = new JPanel() {
            private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage image = ImageIO.read(new File("images/background.jpg")); 
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        
        frmInit.setContentPane(panel);
		
        JRadioButton jrbPastel = new JRadioButton("Colores pastel");
		jrbPastel.setHorizontalAlignment(SwingConstants.CENTER);
		jrbPastel.setFont(new Font("Monospaced", Font.BOLD, 14));
		jrbPastel.setBackground(new Color(255, 121, 206));
		jrbPastel.setBorder(null);
		jrbPastel.setForeground(new Color(18, 2, 43));
		buttonGroupColor.add(jrbPastel);
		jrbPastel.setBounds(398, 322, 205, 40);
		panel.add(jrbPastel);
		
		
		JRadioButton jrbGreen = new JRadioButton("Colores tradicionales");
		jrbGreen.setHorizontalAlignment(SwingConstants.CENTER);
		jrbGreen.setBounds(168, 322, 205, 40);
		panel.add(jrbGreen);
		jrbGreen.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		jrbGreen.setFont(new Font("Monospaced", Font.BOLD, 14));
		jrbGreen.setBackground(new Color(255, 121, 206));
		jrbGreen.setSelected(true);
		jrbGreen.setForeground(new Color(18, 2, 43));
		buttonGroupColor.add(jrbGreen);
        
		JRadioButton jrb4 = new JRadioButton("Tamaño clasic 4x4");
		jrb4.setHorizontalAlignment(SwingConstants.CENTER);
		jrb4.setFont(new Font("Monospaced", Font.BOLD, 14));
		jrb4.setBackground(new Color(255, 121, 206));
		jrb4.setForeground(new Color(18, 2, 43));
		jrb4.setSelected(true);
		buttonGroupSize.add(jrb4);
		jrb4.setBounds(168, 381, 205, 40);
		panel.add(jrb4);
		
		JRadioButton jrb5 = new JRadioButton("Tamaño large 5x5");
		jrb5.setHorizontalAlignment(SwingConstants.CENTER);
		jrb5.setFont(new Font("Monospaced", Font.BOLD, 14));
		jrb5.setBackground(new Color(255, 121, 206));
		jrb5.setForeground(new Color(18, 2, 43));
		buttonGroupSize.add(jrb5);
		jrb5.setBounds(398, 381, 205, 40);
		panel.add(jrb5);
		
        JButton btnNuevoJuego = new JButton("Nuevo Juego");
        btnNuevoJuego.setBorderPainted(false);
        btnNuevoJuego.setFont(new Font("Monospaced", Font.BOLD, 25));
        btnNuevoJuego.setForeground(Color.WHITE);
        btnNuevoJuego.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNuevoJuego.setBackground(new Color(255, 0, 104));
		
		btnNuevoJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String modeColor = "";
				int size = 4;
				if (jrbPastel.isSelected()) {
					modeColor = "PASTEL";
				} 
				if (jrbGreen.isSelected()) {
					modeColor = "GREEN";
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
		btnNuevoJuego.setBounds(168, 189, 435, 48);
		frmInit.getContentPane().add(btnNuevoJuego);
		
		JButton btnMejoresPuntajes = new JButton("Mejores Puntajes");
		btnMejoresPuntajes.setFont(new Font("Monospaced", Font.BOLD, 25));
		btnMejoresPuntajes.setForeground(new Color(18, 2, 43));
		btnMejoresPuntajes.setBackground(new Color(255, 121, 206));
		btnMejoresPuntajes.setBorder(null);
		btnMejoresPuntajes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmScores fs = new FrmScores();
				fs.setVisible(true);
			}
		});
		btnMejoresPuntajes.setBounds(168, 255, 435, 48);
		frmInit.getContentPane().add(btnMejoresPuntajes);
		
	}
}
