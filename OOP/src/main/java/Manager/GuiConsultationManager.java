package Manager;

import Model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import static Manager.WestminsterSkinConsultationManager.*;

public class GuiConsultationManager implements SkinConsultationManager {
    private static LinkedHashMap<String, Patient> PATIENT_LIST = new LinkedHashMap<>();
    private static LinkedList<Availability> AVAILABILITY_LINKED_LIST = new LinkedList<>();
    private static LinkedList<Consultation> CONSULTATION_LINKED_LIST = new LinkedList<>();

    //Getting last AppointmentTime
    public static LocalTime getLastAppointmentTime(String medicalLc , LocalDate date){
        LocalTime sessionEndTime = null;
        for (Consultation consultation:CONSULTATION_LINKED_LIST) {

            LocalDate givenDate = consultation.getAvailability().getDate();
            String givenDoctorMedicalLc = consultation.getAvailability().getDoctor().getMedicalLc();

            if(givenDate.equals(date) && givenDoctorMedicalLc.equals(medicalLc))
                sessionEndTime = consultation.getSessionEnd();
        }
        return sessionEndTime;
    }

    //Check Add Patient
    public static boolean checkAddPatient(Patient patient){return PATIENT_LIST.containsKey(patient.getUniqueId());}


    //Generics S - String T - OBJ LinkedHashMap TO ArrayList
    public static <T extends Person> ArrayList<T> getList(LinkedHashMap<String,T> toList){
        ArrayList<T> list = new ArrayList<>();
        toList.forEach((k,v)-> list.add(v));
        return list;
    }

    //Sort Doctor List
    public static ArrayList<Doctor> sortByName(){return getList(sortByNameList());}
    public static ArrayList<Doctor> sortBySurname(){return getList(sortBySurnameList());}


    //Get Doctor list from Console
    public static ArrayList<Doctor> getDoctorList(){return getConsoleDoctorList();}

    //add Data to List
    public static void addAppointmentList(Availability availability){AVAILABILITY_LINKED_LIST.add(availability);}
    public static void addConsultationList(Consultation consultation){CONSULTATION_LINKED_LIST.add(consultation);}
    public static void addPatientList(Patient patient){PATIENT_LIST.put(patient.getUniqueId(),patient);}

    //get Data from List
    public static ArrayList<Availability> getAvailableDoctorList(){return new ArrayList<>(AVAILABILITY_LINKED_LIST);}
    public static ArrayList<Patient> getPatientList(){return getList(PATIENT_LIST);}
    public static ArrayList<Consultation> getConsultationList(){return new ArrayList<>(CONSULTATION_LINKED_LIST);}


    //For calculate fee First Time 1h - $15 or normally 1h - &25
    public static boolean isPatientThere(String nicNumber) {
        for (Consultation consultation:CONSULTATION_LINKED_LIST) {
            String givenId = consultation.getPatient().getUniqueId();
            if(givenId.equals(nicNumber))
                return true;
        }
        return false;
    }

    //Patient cannot set appointment Same availability doctor twice
    public static boolean checkConsultation(Consultation consultation){
        LinkedList<Consultation> copyConsultationList = new LinkedList<>(CONSULTATION_LINKED_LIST);
        for (Consultation getConsultation : copyConsultationList){
            String selectedPatientID = consultation.getPatient().getUniqueId();
            if(consultation.getAvailability().equals(getConsultation.getAvailability()) &&
                    selectedPatientID.equals(getConsultation.getPatient().getUniqueId()))
                return true;
        }
        return  false;
    }

    //Doctor cannot set 2 appointment in same date
    public static boolean checkAvailability(Availability availability){
        LinkedList<Availability> copyAvailabilityList = new LinkedList<>(AVAILABILITY_LINKED_LIST);
        for (Availability ava : copyAvailabilityList){
            if(availability.getDoctor().getMedicalLc().equals(ava.getDoctor().getMedicalLc()) &&
                    availability.getDate().equals(ava.getDate()))
                return true;
        }
        return  false;
    }

    //Set Load data
    public static void setAppointmentList(LinkedList<Availability> linkedList){
        AVAILABILITY_LINKED_LIST = linkedList;
    }

    public static void setPatientList(LinkedHashMap<String,Patient> linkedHashMap){
        PATIENT_LIST = linkedHashMap;
    }

    public static void setConsultationLinkedList(LinkedList<Consultation> linkedList){
        CONSULTATION_LINKED_LIST = linkedList;
    }

    public static void deleteConsultationLinkedList(Consultation consultation){
        CONSULTATION_LINKED_LIST.remove(consultation);
    }

}
