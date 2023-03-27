package GUI.View;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class PatientConsultationFoam extends  JFrame{

	private final JTextField patientId;
	private final JTable table;
	private final JButton btnSearch;
	private final JButton btnView;
	private final JComboBox<String> comboBoxConsultation;

	public PatientConsultationFoam() {
		setSize(1100, 450);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		
		JLabel lblPatientId = new JLabel("Patient Id");
		lblPatientId.setFont(new Font("Arial", Font.PLAIN, 18));
		lblPatientId.setBounds(31, 39, 87, 16);
		add(lblPatientId);

		JLabel lblConsultation = new JLabel("Consultation");
		lblConsultation.setFont(new Font("Arial", Font.PLAIN, 18));
		lblConsultation.setBounds(450, 39, 100, 16);
		add(lblConsultation);

		patientId = new JTextField();
		patientId.setHorizontalAlignment(SwingConstants.CENTER);
		patientId.setFont(new Font("Arial", Font.PLAIN, 15));
		patientId.setColumns(10);
		patientId.setBackground(new Color(200, 251, 198));
		patientId.setBounds(130, 26, 141, 44);
		add(patientId);
		
		btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Arial", Font.PLAIN, 15));
		btnSearch.setBackground(new Color(241, 242, 246));
		btnSearch.setBounds(285, 28, 121, 42);
		add(btnSearch);

		comboBoxConsultation = new JComboBox<>();
		comboBoxConsultation.setFont(new Font("Arial", Font.PLAIN, 15));
		comboBoxConsultation.setBounds(560, 28, 100, 42);
		add(comboBoxConsultation);

		btnView = new JButton("View");
		btnView.setFont(new Font("Arial", Font.PLAIN, 15));
		btnView.setBackground(new Color(241, 242, 246));
		btnView.setBounds(670, 28, 121, 42);
		add(btnView);

		//JTable
		table = new JTable();
		String[] colNames = {"Index","Doctor ID","Doctor Name","Specialization","Patient ID","Name","Mobile",
				"Session Date","Session Time","Duration","Fee"};
		Object[][] data = new Object[][]{};
		TableModel model = new DefaultTableModel(data,colNames);
		table.setModel(model);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setGridColor(Color.BLACK);
		table.setRowHeight(30);
		table.setEnabled(false);
		JPanel tablePanel = new JPanel();
		scrollPane.add(tablePanel);
		scrollPane.setBounds(20, 100,1050,300);
		add(scrollPane);

		setVisible(true);
	}

	public JTextField getPatientId() {
		return patientId;
	}

	public JTable getTable() {
		return table;
	}

	public JButton getBtnSearch() {
		return btnSearch;
	}

	public JComboBox<String> getComboBoxConsultation() {
		return comboBoxConsultation;
	}

	public JButton getBtnView() {
		return btnView;
	}

}
