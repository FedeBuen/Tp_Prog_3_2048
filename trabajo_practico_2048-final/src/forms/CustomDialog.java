package forms;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	public CustomDialog(String imagePath, String mesage) {
		setTitle(mesage);
		setSize(800, 600);

		// Panel principal que contendrá la imagen y los botones
		JPanel mainPanel = new JPanel(new BorderLayout());

		// Panel para la imagen
		JPanel imagenPanel = new JPanel(new BorderLayout());
		JLabel imageLabel = new JLabel(new ImageIcon(imagePath));
		imagenPanel.add(imageLabel);

		// Panel para los botones
		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton backButton = new JButton("Volver");
		JButton rankingButton = new JButton("Ranking");
		buttonPanel.add(backButton);
		buttonPanel.add(rankingButton);

		mainPanel.add(imageLabel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		setContentPane(mainPanel);
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla

		rankingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmScores fs = new FrmScores();
				fs.setVisible(true);
			}
		});

		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

	}

}
