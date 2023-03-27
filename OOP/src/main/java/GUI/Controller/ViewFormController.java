package GUI.Controller;

import GUI.View.ViewForm;
import Manager.GuiConsultationManager;
import Model.Doctor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

import static Manager.WestminsterSkinConsultationManager.getConsoleDoctorList;

public class ViewFormController {
    private final ViewForm viewForm;

    public ViewFormController(){
        viewForm = new ViewForm();
        getDoctorData();
        viewForm.getViewB1().addActionListener(e->{
            if(viewForm.getViewRB1().isSelected()){
                sortByName();
            } else if (viewForm.getViewRB2().isSelected()) {
                sortBySurname();
            } else{
                JOptionPane.showMessageDialog(null,"Select a Option to sort","Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        viewForm.getViewB2().addActionListener(e->{
            viewForm.getViewGroup().clearSelection();
            clearTable();
            getDoctorData();
        });
    }

    //Set Doctor Data on startUp
    public void getDoctorData() {
        ArrayList<Doctor> doctors = getConsoleDoctorList();
        doctors.forEach(this::setTableData);
    }

    //Sort By name doctorList
    private void sortByName(){
        ArrayList<Doctor> doctors = GuiConsultationManager.sortByName();
        clearTable();
        doctors.forEach(this::setTableData);
    }

    //Sort By surname doctorList
    private void sortBySurname(){
        ArrayList<Doctor> doctors = GuiConsultationManager.sortBySurname();
        clearTable();
        doctors.forEach(this::setTableData);
    }

    //set Table data
    private void setTableData(Doctor doctor){
        DefaultTableModel model = (DefaultTableModel) viewForm.getTable().getModel();
        model.addRow(new Object[]{
                doctor.getName(),
                doctor.getSurname(),
                doctor.getBirthDate(),
                doctor.getMobile(),
                doctor.getMedicalLc(),
                doctor.getSpec()
        });
    }

    //clear table rows
    private void clearTable(){
        ((DefaultTableModel) viewForm.getTable().getModel()).setRowCount(0);
    }
}
