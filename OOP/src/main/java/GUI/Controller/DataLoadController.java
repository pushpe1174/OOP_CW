package GUI.Controller;

import Model.*;

import javax.swing.*;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import static Manager.GuiConsultationManager.*;
import static Manager.WestminsterSkinConsultationManager.setDoctorList;

public class DataLoadController {

    private static final LinkedHashMap<String, Patient> PATIENT_LIST = new LinkedHashMap<>();
    private static final LinkedList<Availability> AVAILABILITY_LINKED_LIST = new LinkedList<>();
    private static final LinkedList<Consultation> CONSULTATION_LINKED_LIST = new LinkedList<>();
    private static final LinkedHashMap<String, Doctor> DOCTOR_LIST = new LinkedHashMap<>();


    @SuppressWarnings("unchecked") //To Avoid Unchecked Cast
    private static<T extends SavableObject> boolean loadLinkList(String fileSource, LinkedList<T> linkedList){
        try(
                FileInputStream fileInput = new FileInputStream(fileSource);
                ObjectInputStream ois = new ObjectInputStream(fileInput))
        {
            for (;;){
                T Object;
                try{
                    Object = (T) ois.readObject();
                }catch (EOFException e){
                    break;
                }
                linkedList.add(Object);
            }
        }catch (IOException e){
            System.out.println("File not found" + fileSource);
            return false;
        }catch (ClassNotFoundException e) {
            System.out.println("Something caused error ->" + fileSource);
            return false;
        }
        System.out.println("\t\t Successfully load - > " + fileSource);
        return true;
    }

    private static boolean loadDoctorList(){
        try(
                FileInputStream fileInput = new FileInputStream("src/main/java/Save/DoctorList.txt");
                ObjectInputStream ois = new ObjectInputStream(fileInput))
        {
            for (;;){
                Doctor Object;
                try{
                    Object = (Doctor) ois.readObject();
                }catch (EOFException e){
                    break;
                }
                DataLoadController.DOCTOR_LIST.put(Object.getMedicalLc(),Object);
            }
        }catch (IOException e){
            System.out.println("File not found DoctorList.txt");
            return false;
        }catch (ClassNotFoundException e) {
            System.out.println("Something caused error ->" + "DoctorList.txt");
            return false;
        }
        System.out.println("\t\t Successfully load - > " + "DoctorList.txt");
        return true;
    }

    private static boolean loadPatientList(){
        try(
                FileInputStream fileInput = new FileInputStream("src/main/java/Save/PatientList.txt");
                ObjectInputStream ois = new ObjectInputStream(fileInput))
        {
            for (;;){
                Patient Object;
                try{
                    Object = (Patient) ois.readObject();
                }catch (EOFException e){
                    break;
                }
                DataLoadController.PATIENT_LIST.put(Object.getUniqueId(),Object);
            }
        }catch (IOException e){
            System.out.println("File not found PatientList.txt");
            return false;
        }catch (ClassNotFoundException e) {
            System.out.println("Something caused error ->" + "PatientList.txt");
            return false;
        }
        System.out.println("\t\t Successfully load - > " + "PatientList.txt");
        return true;
    }


    public static void load(){
        if(checkLoad()){
            JOptionPane.showMessageDialog(null,"Data Successfully Loaded","Success"
                    ,JOptionPane.INFORMATION_MESSAGE);

            //Save Load data to array
            setAppointmentList(AVAILABILITY_LINKED_LIST);
            setConsultationLinkedList(CONSULTATION_LINKED_LIST);
            setPatientList(PATIENT_LIST);
            setDoctorList(DOCTOR_LIST);

        }else{
            JOptionPane.showMessageDialog(null,"Unsuccessfully","Error"
                    ,JOptionPane.ERROR_MESSAGE);
        }
    }

    private static boolean checkLoad() {
        return loadLinkList("src/main/java/Save/AvailableDoctorList.txt",AVAILABILITY_LINKED_LIST) &&
                loadLinkList("src/main/java/Save/ConsultationList.txt",CONSULTATION_LINKED_LIST) &&
                loadDoctorList()&&
                loadPatientList();

    }
}

