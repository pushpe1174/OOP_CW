package GUI.View;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class PatientData extends JFrame {

	private final JTextArea textArea;
	private final JButton btnDecrypt;
	private final JButton btnOpen;

	public PatientData() {
		setBounds(100, 100, 488, 329);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Note");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(19, 31, 45, 16);
		add(lblNewLabel_1);
		
		textArea = new JTextArea();
		textArea.setBounds(93, 32, 361, 161);
		textArea.setEditable(false);
		add(textArea);
		
		btnOpen = new JButton("Open ");
		btnOpen.setFont(new Font("Arial", Font.PLAIN, 15));
		btnOpen.setBounds(93, 228, 117, 52);
		add(btnOpen);

		btnDecrypt = new JButton("Decrypt");
		btnDecrypt.setFont(new Font("Arial", Font.PLAIN, 15));
		btnDecrypt.setBounds(300, 228, 117, 52);
		add(btnDecrypt);
		
		JLabel lblFile = new JLabel("File");
		lblFile.setFont(new Font("Arial", Font.PLAIN, 18));
		lblFile.setBounds(19, 228, 45, 16);
		add(lblFile);

		setVisible(true);
	}

	public JButton getBtnDecrypt() {
		return btnDecrypt;
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public JButton getBtnOpen() {
		return btnOpen;
	}
}
