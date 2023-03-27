package Manager;

import Console.Exception.CentreIsFullException;
import Console.Exception.DoctorExistException;
import Model.Doctor;
import Model.Person;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {
    private static LinkedHashMap<String, Doctor> DOCTOR_LIST = new LinkedHashMap<>();

    public static String addDoctor(Doctor doctor) throws Exception {
        if(DOCTOR_LIST.size() < 10){
            if(!DOCTOR_LIST.containsKey(doctor.getMedicalLc())){
                DOCTOR_LIST.put(doctor.getMedicalLc(),doctor);
                return("\t\t Doctor Successfully added \n");
            }else {
                throw new DoctorExistException();
            }
        }else{
            throw new CentreIsFullException();
        }
    }

    public static void printDoctorList(){
        if(sortBySurnameList().isEmpty()){
            System.out.println("\t\t Nothing to display");
        }else{
            sortBySurnameList().forEach((k, v)->{
                System.out.println("\t\t Medical licence = " + k);
                System.out.println(v.toString());
                System.out.println();
            });
        }
    }
    public static void printDoctor(String medicalLc){
        String doctorDetails = DOCTOR_LIST.get(medicalLc).toString();
        System.out.println(doctorDetails);
    }
    public static LinkedHashMap<String, Doctor> sortBySurnameList() {
        //Custom comparator
        Comparator<Doctor> surname = Comparator.comparing(Person::getSurname);

        return DOCTOR_LIST.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(surname))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (d1, d2) -> d1, LinkedHashMap::new));
    }

    public static LinkedHashMap<String, Doctor> sortByNameList() {
        //Custom comparator
        Comparator<Doctor> name = Comparator.comparing(Person::getName);

        return DOCTOR_LIST.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(name))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (d1, d2) -> d1, LinkedHashMap::new));
    }

    public static void deleteDoctor(String medicalLc) throws NullPointerException{
        int previousListSize = getListSize();
        printDoctor(medicalLc);
        DOCTOR_LIST.remove(medicalLc);
        if(getListSize()<previousListSize)
            System.out.println("\n\t\t Doctor successfully removed");
        else
            System.out.println("\n\t\t Doctor successfully not removed");
    }

    public static  void saveFile() {
        try(
                FileOutputStream fileOutput= new FileOutputStream("text.txt");
                ObjectOutputStream os = new ObjectOutputStream(fileOutput))
        {
            DOCTOR_LIST.forEach((k,v)->{
                try {
                    os.writeObject(v);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });
            System.out.println("\t\t Successfully saved to the file");
        }catch (IOException e){
            System.out.println("File not Found");
        }

    }

    public static void loadFile(String source) throws IOException{
        try(
                FileInputStream fileInput = new FileInputStream(source);
                ObjectInputStream ois = new ObjectInputStream(fileInput))
        {
            for (;;){
                Doctor doctor;
                try{
                     doctor= (Doctor) ois.readObject();
                }catch (EOFException e){
                    break;
                }
                DOCTOR_LIST.put(doctor.getMedicalLc(),doctor);
            }
            System.out.println("\t\t Successfully load");

        }catch (ClassNotFoundException e) {
            System.out.println("Something caused error");
        }
    }

    public static boolean isListFull(){
        return (DOCTOR_LIST.size() < 10);
    }

    public static int getListSize(){return DOCTOR_LIST.size();}

    public static boolean isListEmpty(){return  DOCTOR_LIST.isEmpty();}

    public static ArrayList<Doctor> getConsoleDoctorList(){
        ArrayList<Doctor> doctors= new ArrayList<>();
        DOCTOR_LIST.forEach((k,v)-> doctors.add(v));
        return doctors;
    }

    public static void setDoctorList(LinkedHashMap<String, Doctor> linkedHashMap) {
        DOCTOR_LIST = linkedHashMap;
    }

}
