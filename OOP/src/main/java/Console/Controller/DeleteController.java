package Console.Controller;

import Console.View.Design;
import java.util.Scanner;

import static Manager.WestminsterSkinConsultationManager.*;

public class DeleteController {
    public static void deleteMenuOption() {
        if(isListEmpty()){
            System.out.println("\t\t No Doctor to delete");
        }else{
            System.out.print("\t\t Enter Medical Lc - ");
            Scanner input = new Scanner(System.in);
            try{
                deleteDoctor(input.next());
                System.out.println("\t\t " + getListSize() + " doctors remaining in the centre.");
            }catch (NullPointerException e){
                System.out.println("\t\t " + "Error: No such a Doctor to Delete");
            }

        }
        quesDeleteMenu();
    }


    private static void quesDeleteMenu(){
        Scanner input = new Scanner(System.in);
        System.out.print("\n\t\t Do you want to delete a Doctor(Y/n) ?");

        if(input.next().equals("Y")){
            Design.deleteMenu();
        }else {
            Design.mainMenu();
        }
    }
}
