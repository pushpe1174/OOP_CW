package GUI.Controller;

import GUI.View.AvailabilityFoam;
import Manager.GuiConsultationManager;
import Model.Availability;
import Model.Doctor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ItemEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

import static Manager.GuiConsultationManager.getAvailableDoctorList;
import static Manager.GuiConsultationManager.checkAvailability;
import static Manager.WestminsterSkinConsultationManager.getConsoleDoctorList;

public class AvailabilityController {
    private final AvailabilityFoam availabilityFoam;
    private Doctor selectedDoctor;
    public AvailabilityController(){
        availabilityFoam = new AvailabilityFoam();
        setDoctorList();
        setAvailabilityDoctorList();
        availabilityFoam.getComboBoxDocList().addItemListener(
                e -> {
                    if(e.getStateChange() == ItemEvent.SELECTED) {
                        setLabels(e.getItem().toString());
                    }
                }
        );
        availabilityFoam.getBtnAdd().addActionListener(
                e -> {
                    if(isEmpty()){
                        JOptionPane.showMessageDialog(null,
                                "All fields required","Error",
                                JOptionPane.ERROR_MESSAGE);
                    }else{
                        setAvailabilityDoctor();
                        clearLabels();
                    }
                }
        );
    }

    private void clearLabels() {
        availabilityFoam.getComboBoxDocList().setSelectedItem("");
        availabilityFoam.getStartTime().clear();
        availabilityFoam.getDateChooser().setDate(null);

        availabilityFoam.getTxtDocName().setText("");
        availabilityFoam.getTxtDocSurname().setText("");
        availabilityFoam.getTxtDocSpec().setText("");

    }

    //Set Table Row
    private void setTableRow(Availability availability){
        //Time Formatter
        DefaultTableModel model = (DefaultTableModel) availabilityFoam.getTable().getModel();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        Doctor doctor = availability.getDoctor();
        String startTime = timeFormatter.format(availability.getStartTime());

        model.addRow(new Object[]{
                doctor.getMedicalLc(),
                doctor.getName(),
                doctor.getSurname(),
                doctor.getMobile(),
                doctor.getSpec(),
                availability.getDate(),
                startTime
        });
    }

    //Startup Data Set
    private void setAvailabilityDoctorList() {
        ArrayList<Availability> availabilityArrayList = getAvailableDoctorList();
        availabilityArrayList.forEach(this::setTableRow);
    }

    //Add Available Doctor
    private void setAvailabilityDoctor() {
        Availability availabilityDoctor = new Availability();
        availabilityDoctor.setDoctor(selectedDoctor);

        //Date Format
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy,MM,dd");
        String birthDate = dataFormat.format(availabilityFoam.getDateChooser().getDate());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy,MM,dd");
        LocalDate localDate = LocalDate.parse(birthDate,dateTimeFormatter);
        availabilityDoctor.setDate(localDate);

        //Time
        availabilityDoctor.setStartTime(availabilityFoam.getStartTime().getTime());

        if(checkAvailability(availabilityDoctor)){
            JOptionPane.showMessageDialog(null,
                    "Doctor cannot added","Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            //Table data
            setTableRow(availabilityDoctor);
            GuiConsultationManager.addAppointmentList(availabilityDoctor);
            JOptionPane.showMessageDialog(null,
                    "Doctor Availability added","Success",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }


    //Check fields are Empty
    private boolean isEmpty(){
        if (Objects.equals(availabilityFoam.getComboBoxDocList().getSelectedItem(), ""))
            return true;
        if (availabilityFoam.getStartTime().getTime() == null)
            return true;
        return availabilityFoam.getDateChooser().getDate() == null;
    }

    //Set Doctor Details
    private void setLabels(String medicalLC){
        ArrayList<Doctor> doctorArrayList = getConsoleDoctorList();
        for (Doctor doctor:doctorArrayList) {
            if (doctor.getMedicalLc().equals(medicalLC)) {
                availabilityFoam.getTxtDocName().setText(doctor.getName());
                availabilityFoam.getTxtDocSurname().setText(doctor.getSurname());
                availabilityFoam.getTxtDocSpec().setText(doctor.getSpec());
                selectedDoctor = doctor;
                break;
            }
        }
    }

    //Set Doctor ComboBox
    private void setDoctorList(){
        ArrayList<Doctor> doctorArrayList = getConsoleDoctorList();
        availabilityFoam.getComboBoxDocList().addItem("");
        doctorArrayList.forEach(v-> availabilityFoam.getComboBoxDocList().addItem(v.getMedicalLc()));
    }
}
