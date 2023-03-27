package GUI.Controller;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

import static Manager.GuiConsultationManager.*;


public class DataSaveController {

    //Using static generic method for save files
    private static <T extends Serializable> boolean saveFile (String saveFileName, ArrayList<T> LIST) {
        //File Obj
        File mainSource = new File("src/main/java/Save/");

        //Check exist or create File
        System.out.println(mainSource.mkdir()? "Created Save Folder" : "Using It - For Save");

        //Set Path for File
        String path = "src/main/java/Save/" + saveFileName ;

        //Using a resource try catch for serializable Obj
        try(
                FileOutputStream fileOutput= new FileOutputStream(path);
                ObjectOutputStream os = new ObjectOutputStream(fileOutput))
        {
            LIST.forEach((v)->{
                try {
                    os.writeObject(v);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });
        }catch (IOException e){
            System.out.println("Check directory!!!" + saveFileName);
            return false;
        }
        System.out.println(saveFileName + " Successfully Saved");
        return true;
    }

    //Check error
    private static boolean saveDoctorList(){
        return saveFile("DoctorList.txt",getDoctorList());
    }
    private static boolean savePatientList(){
        return saveFile("PatientList.txt",getPatientList());
    }
    private static boolean saveAvailableDoctorList(){
        return saveFile("AvailableDoctorList.txt",getAvailableDoctorList());
    }
    private static boolean saveConsultationList(){
        return saveFile("ConsultationList.txt",getConsultationList());
    }


    private static boolean checkSave(){
        return saveDoctorList() && savePatientList() && saveAvailableDoctorList() && saveConsultationList();
    }

    public static void save(){
        if(checkSave()){
            JOptionPane.showMessageDialog(null,"Data Successfully Saved","Success"
                    ,JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null,"Unsuccessfully","Error"
                    ,JOptionPane.ERROR_MESSAGE);
        }
    }
}
