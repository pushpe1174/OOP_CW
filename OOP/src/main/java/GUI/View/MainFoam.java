package GUI.View;

import GUI.Controller.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;

import static GUI.Controller.DataLoadController.load;
import static GUI.Controller.DataSaveController.save;

public class MainFoam extends JFrame {
	private MainFoam (){
		setBounds(100, 100, 604, 420);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);

		JLabel lblSkinConsultationManager = new JLabel("Skin Consultation Manager");
		lblSkinConsultationManager.setForeground(new Color(44, 52, 54));
		lblSkinConsultationManager.setFont(new Font("Arial", Font.BOLD, 25));
		lblSkinConsultationManager.setBounds(144, 30, 346, 42);
		add(lblSkinConsultationManager);

		//View Doctor
		JButton btnViewDoctor = new JButton("View Doctors List");
		btnViewDoctor.setFont(new Font("Arial", Font.PLAIN, 15));
		btnViewDoctor.setBounds(47, 97, 235, 51);
		btnViewDoctor.addActionListener(
				e -> new ViewFormController()
		);
		add(btnViewDoctor);

		//View Patient
		JButton btnViewConsultation = new JButton("View Consultations");
		btnViewConsultation.setFont(new Font("Arial", Font.PLAIN, 15));
		btnViewConsultation.setBounds(314, 97, 235, 51);
		btnViewConsultation.addActionListener(
				e -> new PatientConsultationController()
		);
		add(btnViewConsultation);


		//View Patient Data
		JButton btnSetAvailability = new JButton("Set Doctor Availability");
		btnSetAvailability.setFont(new Font("Arial", Font.PLAIN, 15));
		btnSetAvailability.setBounds(314, 180, 235, 51);
		btnSetAvailability.addActionListener(e-> new AvailabilityController());
		add(btnSetAvailability);

		//Set Consultation
		JButton btnSetConsultation = new JButton("Set Consultation");
		btnSetConsultation.setFont(new Font("Arial", Font.PLAIN, 15));
		btnSetConsultation.setBounds(47, 180, 235, 51);
		btnSetConsultation.addActionListener(e -> new ConsultationController());
		add(btnSetConsultation);

		//Save AllData
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Arial", Font.PLAIN, 15));
		btnSave.setBounds(47, 268, 235, 51);
		btnSave.addActionListener(e -> save());
		add(btnSave);

		//Load AllData
		JButton btnLoad = new JButton("Load");
		btnLoad.setFont(new Font("Arial", Font.PLAIN, 15));
		btnLoad.setBounds(314,268, 235, 51);
		btnLoad.addActionListener(e-> load());
		add(btnLoad);


		//For visible
		setResizable(false);
		setVisible(true);
	}

	public static void getMainFoam(){
		new MainFoam();
	}
}
