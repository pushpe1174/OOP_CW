package GUI.View;

import com.github.lgooddatepicker.components.TimePicker;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;

public class ConsultationFoam extends JFrame {
	private final JTextField txtDocName;
	private final JTextField txtDocAvailability;
	private final JTextField txtDocSurname;
	private final JTextField txtDocSpec;
	private final JComboBox<String> comboBoxDocList;
	private final JDateChooser dateChooser;

	private final TimePicker startTime;
	private final TimePicker endTime;

	private final JButton btnSave;
	private final JButton btnDocCheck;


	private final JTextField txtPatientNic;
	private final JTextField txtPatientName;
	private final JTextField txtPatientSurname;
	private final JTextField txtPatientMobile;
	private final JDateChooser bDateChooser;
	private final ButtonGroup gender;
	private final JRadioButton rdBtnMale;
	private final JRadioButton rdBtnFemale;
	private final JButton btnCreate;
	private final JButton btnPatientCheck;
	private final JButton btnReset;
	private final JTextArea patientNotes;
	private final JComboBox<String> comboBoxDuration;

	private final TextField fileName;

	private final JButton btnCheckFile;

	public ConsultationFoam() {
		setSize(862, 730);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblCheckDoctor = new JLabel("Check Doctor Details");
		lblCheckDoctor.setForeground(new Color(44, 52, 54));
		lblCheckDoctor.setFont(new Font("Arial", Font.BOLD, 20));
		lblCheckDoctor.setBounds(22, 17, 373, 42);
		add(lblCheckDoctor);
		
		JLabel lblDoctor = new JLabel("Doctor");
		lblDoctor.setFont(new Font("Arial", Font.PLAIN, 18));
		lblDoctor.setBounds(52, 74, 61, 16);
		add(lblDoctor);

		//Doctor ComboBox
		comboBoxDocList = new JComboBox<>();
		comboBoxDocList.setFont(new Font("Arial", Font.PLAIN, 15));
		comboBoxDocList.setBackground(new Color(241, 242, 246));
		comboBoxDocList.setBounds(149, 71, 117, 27);
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

		//End Time
		endTime = new TimePicker();
		endTime.setFont(new Font("Arial", Font.PLAIN, 15));
		endTime.setBackground(new Color(241, 242, 246));
		endTime.setBounds(300, 126, 140, 30);
		add(endTime);

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
		

		JLabel lblSign = new JLabel("-");
		lblSign.setFont(new Font("Arial", Font.PLAIN, 18));
		lblSign.setBounds(290, 111, 61, 53);
		add(lblSign);

		JLabel lblDoctorDetails = new JLabel("Doctor Details");
		lblDoctorDetails.setForeground(new Color(44, 52, 54));
		lblDoctorDetails.setFont(new Font("Arial", Font.BOLD, 20));
		lblDoctorDetails.setBounds(468, 17, 169, 42);
		add(lblDoctorDetails);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Arial", Font.PLAIN, 18));
		lblName.setBounds(478, 66, 101, 31);
		add(lblName);
		
		JLabel lblAvailableTime = new JLabel("Available Time");
		lblAvailableTime.setFont(new Font("Arial", Font.PLAIN, 18));
		lblAvailableTime.setBounds(478, 180, 136, 16);
		add(lblAvailableTime);
		
		JLabel lblSpec = new JLabel("Specialization");
		lblSpec.setFont(new Font("Arial", Font.PLAIN, 18));
		lblSpec.setBounds(479, 223, 154, 32);
		add(lblSpec);
		
		txtDocName = new JTextField();
		txtDocName.setHorizontalAlignment(SwingConstants.CENTER);
		txtDocName.setFont(new Font("Arial", Font.PLAIN, 15));
		txtDocName.setColumns(10);
		txtDocName.setBounds(650, 60, 141, 44);
		txtDocName.setBackground(new Color(241, 242, 246));
		txtDocName.setEditable(false);
		add(txtDocName);
		
		txtDocAvailability = new JTextField();
		txtDocAvailability.setHorizontalAlignment(SwingConstants.CENTER);
		txtDocAvailability.setFont(new Font("Arial", Font.PLAIN, 15));
		txtDocAvailability.setColumns(10);
		txtDocAvailability.setBounds(650, 166, 141, 44);
		txtDocAvailability.setBackground(new Color(241, 242, 246));
		txtDocAvailability.setEditable(false);
		add(txtDocAvailability);

		txtDocSurname = new JTextField();
		txtDocSurname.setHorizontalAlignment(SwingConstants.CENTER);
		txtDocSurname.setFont(new Font("Arial", Font.PLAIN, 15));
		txtDocSurname.setColumns(10);
		txtDocSurname.setBounds(650, 116, 141, 43);
		txtDocSurname.setBackground(new Color(241, 242, 246));
		txtDocSurname.setEditable(false);
		add(txtDocSurname);

		txtDocSpec = new JTextField();
		txtDocSpec.setHorizontalAlignment(SwingConstants.CENTER);
		txtDocSpec.setFont(new Font("Arial", Font.PLAIN, 15));
		txtDocSpec.setColumns(10);
		txtDocSpec.setBounds(650, 220, 141, 44);
		txtDocSpec.setBackground(new Color(241, 242, 246));
		txtDocSpec.setEditable(false);
		add(txtDocSpec);

		JLabel lblPatientDetails = new JLabel("Patient Details");
		lblPatientDetails.setForeground(new Color(44, 52, 54));
		lblPatientDetails.setFont(new Font("Arial", Font.BOLD, 20));
		lblPatientDetails.setBounds(22, 288, 335, 39);
		add(lblPatientDetails);
		
		JLabel lblNic = new JLabel("NIC Number");
		lblNic.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNic.setBounds(52, 339, 136, 16);
		add(lblNic);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setFont(new Font("Arial", Font.PLAIN, 18));
		lblSurname.setBounds(50, 446, 136, 16);
		add(lblSurname);
		
		JLabel lblDoB = new JLabel("Date of Birth");
		lblDoB.setFont(new Font("Arial", Font.PLAIN, 18));
		lblDoB.setBounds(50, 502, 136, 16);
		add(lblDoB);
		
		JLabel lblNameP = new JLabel("Name");
		lblNameP.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNameP.setBounds(50, 390, 136, 16);
		add(lblNameP);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Arial", Font.PLAIN, 18));
		lblGender.setBounds(50, 606, 136, 16);
		add(lblGender);
		
		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setFont(new Font("Arial", Font.PLAIN, 18));
		lblMobile.setBounds(50, 558, 136, 16);
		add(lblMobile);
		
		
		
		txtPatientSurname = new JTextField();
		txtPatientSurname.setHorizontalAlignment(SwingConstants.CENTER);
		txtPatientSurname.setForeground(Color.BLACK);
		txtPatientSurname.setFont(new Font("Arial", Font.PLAIN, 15));
		txtPatientSurname.setColumns(10);
		txtPatientSurname.setBackground(new Color(241, 242, 246));
		txtPatientSurname.setBounds(200, 433, 141, 44);
		txtPatientSurname.setEditable(false);
		add(txtPatientSurname);
		
		txtPatientNic = new JTextField();
		txtPatientNic.setHorizontalAlignment(SwingConstants.CENTER);
		txtPatientNic.setFont(new Font("Arial", Font.PLAIN, 15));
		txtPatientNic.setColumns(10);
		txtPatientNic.setBackground(new Color(241, 242, 246));
		txtPatientNic.setBounds(200, 326, 141, 44);
		add(txtPatientNic);
		
		txtPatientName = new JTextField();
		txtPatientName.setHorizontalAlignment(SwingConstants.CENTER);
		txtPatientName.setFont(new Font("Arial", Font.PLAIN, 15));
		txtPatientName.setColumns(10);
		txtPatientName.setBackground(new Color(241, 242, 246));
		txtPatientName.setBounds(200, 377, 141, 44);
		txtPatientName.setEditable(false);
		add(txtPatientName);
		
		txtPatientMobile = new JTextField();
		txtPatientMobile.setHorizontalAlignment(SwingConstants.CENTER);
		txtPatientMobile.setFont(new Font("Arial", Font.PLAIN, 15));
		txtPatientMobile.setColumns(10);
		txtPatientMobile.setBackground(new Color(241, 242, 246));
		txtPatientMobile.setBounds(200, 545, 141, 44);
		txtPatientMobile.setEditable(false);
		add(txtPatientMobile);

		bDateChooser = new JDateChooser();
		bDateChooser.setFont(new Font("Arial", Font.PLAIN, 15));
		bDateChooser.setBounds(200, 489, 141, 44);
		bDateChooser.setBackground(new Color(241, 242, 246));
		bDateChooser.getDateEditor().setEnabled(false);
		bDateChooser.getCalendarButton().setEnabled(false);
		add(bDateChooser);


		btnPatientCheck = new JButton("Check");
		btnPatientCheck.setFont(new Font("Arial", Font.PLAIN, 15));
		btnPatientCheck.setBackground(new Color(241, 242, 246));
		btnPatientCheck.setBounds(364, 328, 61, 42);
		add(btnPatientCheck);

		rdBtnMale = new JRadioButton("Male");
		rdBtnMale.setFont(new Font("Arial", Font.PLAIN, 15));
		rdBtnMale.setBounds(198, 603, 63, 23);
		rdBtnMale.setEnabled(false);
		add(rdBtnMale);
		
		rdBtnFemale = new JRadioButton("Female");
		rdBtnFemale.setFont(new Font("Arial", Font.PLAIN, 15));
		rdBtnFemale.setBounds(272, 603, 85, 23);
		rdBtnFemale.setEnabled(false);
		add(rdBtnFemale);

		gender = new ButtonGroup();
		gender.add(rdBtnMale);
		gender.add(rdBtnFemale);

		btnSave = new JButton("Save");
		btnSave.setFont(new Font("Arial", Font.PLAIN, 15));
		btnSave.setBackground(new Color(241, 242, 246));
		btnSave.setBounds(676, 640, 129, 42);
		add(btnSave);
		
		JLabel lblDuration = new JLabel("Duration");
		lblDuration.setFont(new Font("Arial", Font.PLAIN, 18));
		lblDuration.setBounds(496, 341, 78, 16);
		add(lblDuration);

		comboBoxDuration = new JComboBox<>();
		comboBoxDuration.setFont(new Font("Arial", Font.PLAIN, 15));
		comboBoxDuration.setBounds(586, 338, 111, 27);
		comboBoxDuration.addItem("");
		for (int i=15;i<=90;i++){
			comboBoxDuration.addItem(String.valueOf(i));
			i += 14;
		}
		add(comboBoxDuration);
		
		JLabel lblAppointmentDetail = new JLabel("Appointment Details");
		lblAppointmentDetail.setForeground(new Color(44, 52, 54));
		lblAppointmentDetail.setFont(new Font("Arial", Font.BOLD, 20));
		lblAppointmentDetail.setBounds(468, 288, 195, 42);
		add(lblAppointmentDetail);
		
		JLabel lblNotes = new JLabel("Notes");
		lblNotes.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNotes.setBounds(496, 392, 78, 16);
		add(lblNotes);
		
		patientNotes = new JTextArea();
		patientNotes.setBounds(586, 393, 256, 167);
		add(patientNotes);
		
		JLabel lblImages = new JLabel("Images");
		lblImages.setFont(new Font("Arial", Font.PLAIN, 18));
		lblImages.setBounds(496, 586, 78, 20);
		add(lblImages);

		fileName = new TextField();
		fileName.setFont(new Font("Arial", Font.PLAIN, 15));
		fileName.setEditable(false);
		fileName.setBackground(new Color(241, 242, 246));

		JScrollPane scrollPaneForFile = new JScrollPane(fileName);
		scrollPaneForFile.setBounds(586, 578, 120, 35);
		add(scrollPaneForFile);

		btnCheckFile = new JButton("Choose File");
		btnCheckFile.setFont(new Font("Arial", Font.PLAIN, 15));
		btnCheckFile.setBounds(720, 578, 120, 35);
		add(btnCheckFile);


		JLabel lblSurnameP = new JLabel("Surname");
		lblSurnameP.setFont(new Font("Arial", Font.PLAIN, 18));
		lblSurnameP.setBounds(478, 120, 101, 31);
		add(lblSurnameP);
		
		btnCreate = new JButton("Create");
		btnCreate.setFont(new Font("Arial", Font.PLAIN, 15));
		btnCreate.setBackground(new Color(241, 242, 246));
		btnCreate.setBounds(296, 637, 129, 42);
		add(btnCreate);

		btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Arial", Font.PLAIN, 15));
		btnReset.setBackground(new Color(241, 242, 246));
		btnReset.setBounds(150, 637, 129, 42);
		add(btnReset);
		
		btnDocCheck = new JButton("Check");
		btnDocCheck.setFont(new Font("Arial", Font.PLAIN, 15));
		btnDocCheck.setBackground(new Color(241, 242, 246));
		btnDocCheck.setBounds(364, 171, 61, 42);
		add(btnDocCheck);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 267, 836, 18);
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(437, 279, 12, 331);
		add(separator_1);
		
		JLabel lblMin = new JLabel("min");
		lblMin.setFont(new Font("Arial", Font.PLAIN, 18));
		lblMin.setBounds(697, 341, 29, 16);
		add(lblMin);
		
		setVisible(true);
	}

	public JTextField getTxtDocName() {
		return txtDocName;
	}

	public JTextField getTxtDocAvailability() {
		return txtDocAvailability;
	}

	public JTextField getTxtDocSurname() {
		return txtDocSurname;
	}

	public JTextField getTxtDocSpec() {
		return txtDocSpec;
	}

	public JComboBox<String> getComboBoxDocList() {
		return comboBoxDocList;
	}

	public JDateChooser getDateChooser() {
		return dateChooser;
	}

	public TimePicker getStartTime() {
		return startTime;
	}

	public TimePicker getEndTime() {
		return endTime;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

	public JButton getBtnDocCheck() {
		return btnDocCheck;
	}

	public JTextField getTxtPatientNic() {
		return txtPatientNic;
	}

	public JTextField getTxtPatientName() {
		return txtPatientName;
	}

	public JTextField getTxtPatientSurname() {
		return txtPatientSurname;
	}

	public JTextField getTxtPatientMobile() {
		return txtPatientMobile;
	}

	public JDateChooser getBirthDateChooser() {return bDateChooser;}

	public ButtonGroup getGender() {
		return gender;
	}

	public JRadioButton getRdBtnMale() {
		return rdBtnMale;
	}

	public JRadioButton getRdBtnFemale() {
		return rdBtnFemale;
	}

	public JButton getBtnCreate() {
		return btnCreate;
	}

	public JButton getBtnPatientCheck() {
		return btnPatientCheck;
	}

	public JButton getBtnReset() {
		return btnReset;
	}

	public JTextArea getPatientNotes() {
		return patientNotes;
	}

	public JComboBox<String> getComboBoxDuration() {
		return comboBoxDuration;
	}

	public TextField getFileName() {
		return fileName;
	}

	public JButton getBtnCheckFile() {
		return btnCheckFile;
	}
}
