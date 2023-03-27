package GUI.View;

import com.github.lgooddatepicker.components.TimePicker;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class AvailabilityFoam extends JFrame{

	private final JTextField txtDocName;
	private final JTextField txtDocSpec;
	private final JTextField txtDocSurname;
	private final JTable table;

	private final JComboBox<String> comboBoxDocList;
	private final TimePicker startTime;
//	private final TimePicker endTime;

	private final JDateChooser dateChooser;
	private final JButton btnAdd;

	public AvailabilityFoam() {
		setSize(840, 556);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JLabel lblCheckDoctor = new JLabel("Check Doctor Details");
		lblCheckDoctor.setForeground(new Color(44, 52, 54));
		lblCheckDoctor.setFont(new Font("Arial", Font.BOLD, 20));
		lblCheckDoctor.setBounds(22, 17, 373, 42);
		add(lblCheckDoctor);

		JLabel lblDoctorId = new JLabel("Doctor ID");
		lblDoctorId.setFont(new Font("Arial", Font.PLAIN, 18));
		lblDoctorId.setBounds(52, 74, 80, 16);
		add(lblDoctorId);

		//Doctor ComboBox
		comboBoxDocList = new JComboBox<>();
		comboBoxDocList.setFont(new Font("Arial", Font.PLAIN, 15));
		comboBoxDocList.setBackground(new Color(241, 242, 246));
		comboBoxDocList.setBounds(149, 71, 130, 27);
		add(comboBoxDocList);

		JLabel lblTime = new JLabel("Time");
		lblTime.setFont(new Font("Arial", Font.PLAIN, 18));
		lblTime.setBounds(52, 129, 42, 16);
		add(lblTime);

		//Start Time
		startTime = new TimePicker();
		startTime.setFont(new Font("Arial", Font.PLAIN, 15));
		startTime.setBackground(new Color(241, 242, 246));
		startTime.setBounds(149, 126, 140, 30);
		add(startTime);

		JLabel lblDate = new JLabel("Date ");
		lblDate.setFont(new Font("Arial", Font.PLAIN, 18));
		lblDate.setBounds(52, 182, 61, 16);
		add(lblDate);

		//Date
		dateChooser = new JDateChooser();
		dateChooser.setFont(new Font("Arial", Font.PLAIN, 15));
		dateChooser.setBackground(new Color(241, 242, 246));
		dateChooser.setBounds(149, 179, 136, 27);
		dateChooser.getDateEditor().setEnabled(false);
		add(dateChooser);


		//Add Btn
		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Arial", Font.PLAIN, 15));
		btnAdd.setBackground(new Color(241, 242, 246));
		btnAdd.setBounds(343, 172, 75, 42);
		add(btnAdd);

		JLabel lblDoctorDetails = new JLabel("Doctor Details");
		lblDoctorDetails.setForeground(new Color(44, 52, 54));
		lblDoctorDetails.setFont(new Font("Arial", Font.BOLD, 20));
		lblDoctorDetails.setBounds(468, 17, 169, 42);
		add(lblDoctorDetails);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Arial", Font.PLAIN, 18));
		lblName.setBounds(478, 66, 101, 31);
		add(lblName);

		JLabel lblSpec = new JLabel("Specialization");
		lblSpec.setFont(new Font("Arial", Font.PLAIN, 18));
		lblSpec.setBounds(478, 180, 136, 16);
		add(lblSpec);

		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setFont(new Font("Arial", Font.PLAIN, 18));
		lblSurname.setBounds(479, 120, 154, 32);
		add(lblSurname);

		txtDocName = new JTextField();
		txtDocName.setHorizontalAlignment(SwingConstants.CENTER);
		txtDocName.setFont(new Font("Arial", Font.PLAIN, 15));
		txtDocName.setColumns(10);
		txtDocName.setBounds(650, 60, 141, 44);
		txtDocName.setBackground(new Color(241, 242, 246));
		txtDocName.setEditable(false);
		add(txtDocName);

		txtDocSpec = new JTextField();
		txtDocSpec.setHorizontalAlignment(SwingConstants.CENTER);
		txtDocSpec.setFont(new Font("Arial", Font.PLAIN, 15));
		txtDocSpec.setColumns(10);
		txtDocSpec.setBounds(650, 166, 141, 44);
		txtDocSpec.setBackground(new Color(241, 242, 246));
		txtDocSpec.setEditable(false);
		add(txtDocSpec);

		txtDocSurname = new JTextField();
		txtDocSurname.setHorizontalAlignment(SwingConstants.CENTER);
		txtDocSurname.setFont(new Font("Arial", Font.PLAIN, 15));
		txtDocSurname.setColumns(10);
		txtDocSurname.setBounds(650, 116, 141, 43);
		txtDocSurname.setBackground(new Color(241, 242, 246));
		txtDocSurname.setEditable(false);
		add(txtDocSurname);

		//JTable
		table = new JTable();
		String[] colNames = {"Medical LC","Name", "Surname","Mobile","Specialisation","Date","Start Time"};
		Object[][] data = new Object[][]{};
		TableModel model = new DefaultTableModel(data,colNames);
		table.setModel(model);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setGridColor(Color.BLACK);
		table.setRowHeight(30);

		JPanel tablePanel = new JPanel();
		scrollPane.add(tablePanel);
		scrollPane.setBounds(38, 250,700,180);
		add(scrollPane);

		setVisible(true);

	}

	public JTextField getTxtDocName() {
		return txtDocName;
	}

	public JTextField getTxtDocSpec() {
		return txtDocSpec;
	}

	public JTextField getTxtDocSurname() {
		return txtDocSurname;
	}

	public JTable getTable() {
		return table;
	}

	public JComboBox<String> getComboBoxDocList() {
		return comboBoxDocList;
	}

	public TimePicker getStartTime() {
		return startTime;
	}


	public JDateChooser getDateChooser() {
		return dateChooser;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}
}
