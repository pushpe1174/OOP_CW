package GUI.Controller;

import GUI.View.PatientConsultationFoam;
import Model.Consultation;
import Model.Doctor;
import Model.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

import static Manager.GuiConsultationManager.getConsultationList;

public class PatientConsultationController {
    private final PatientConsultationFoam patientConsultationFoam;
    private final ArrayList<Consultation> consultationList;
    private ArrayList<Consultation> selectConsultationList;

    public PatientConsultationController(){
        patientConsultationFoam = new PatientConsultationFoam();
        consultationList = getConsultationList();
        setConsultations();
        setComboBox();

        patientConsultationFoam.getBtnSearch().addActionListener(
                e->{
                    if (patientConsultationFoam.getPatientId().getText().equals("")){
                        setConsultations();
                        setComboBox();
                    }else{
                        searchResult();
                        setComboBox();
                    }
                }
        );

        patientConsultationFoam.getBtnView().addActionListener(
                e -> {
                    if(patientConsultationFoam.getComboBoxConsultation().getSelectedItem() == null){
                        JOptionPane.showMessageDialog(null,"No Consultation","Error",
                                JOptionPane.ERROR_MESSAGE);
                    } else if(getConsultationObject().getNote().equals("") && getConsultationObject().getSourcePath().equals("")){
                        JOptionPane.showMessageDialog(null,"No Extra data for this Consultation","Error",
                                JOptionPane.ERROR_MESSAGE);
                    }else{
                        new PatientDataController(getConsultationObject());
                    }
                }
        );
    }

    private Consultation getConsultationObject() {
        int index = Integer.parseInt(Objects.requireNonNull(patientConsultationFoam.getComboBoxConsultation()
                .getSelectedItem()).toString());
        return selectConsultationList.get(index-1);
    }

    //Set Consultation Combo Box
    public void setComboBox(){
        patientConsultationFoam.getComboBoxConsultation().removeAllItems();
        int row = patientConsultationFoam.getTable().getModel().getRowCount();
        for(int i = 1;i<=row;i++){
            patientConsultationFoam.getComboBoxConsultation().addItem(Integer.toString(i));
        }
    }

    //Clear Table
    private void clearTable(){
        ((DefaultTableModel) patientConsultationFoam.getTable().getModel()).setRowCount(0);
    }

    //Search and set Data
    private void searchResult() {
        selectConsultationList = new ArrayList<>();
        for (Consultation consultation : consultationList) {
            Patient patient = consultation.getPatient();
            if (patient.getUniqueId().equals(patientConsultationFoam.getPatientId().getText())) {
                selectConsultationList.add(consultation);
            }
        }
        clearTable();
        for (int i = 1; i <= Objects.requireNonNull(selectConsultationList).size(); i++) {
            setTableRow(selectConsultationList.get(i-1),i);
        }
    }

    //StartUpSetData
    public void setConsultations(){
        selectConsultationList = new ArrayList<>();
        clearTable();
        for (int i=1;i<=consultationList.size();i++){
            setTableRow(consultationList.get(i-1),i);
            selectConsultationList.add(consultationList.get(i-1));
        }
    }

    //SetTable Data
    private void setTableRow(Consultation selectConsultation, int i){
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        DefaultTableModel model = (DefaultTableModel) patientConsultationFoam.getTable().getModel();
        Patient patient = selectConsultation.getPatient();
        Doctor doctor = selectConsultation.getAvailability().getDoctor();
        String sessionStart = timeFormatter.format(selectConsultation.getSessionStart());
        model.addRow(new Object[]{
                Integer.toString(i),
                doctor.getMedicalLc(),
                doctor.getName(),
                doctor.getSpec(),
                patient.getUniqueId(),
                patient.getName(),
                patient.getMobile(),
                selectConsultation.getAvailability().getDate(),
                sessionStart,
                selectConsultation.getDuration() + "min",
                "€" + selectConsultation.getFee()
        });
    }
}
