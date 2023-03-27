package Console.Controller;

import Console.View.Design;
import java.util.Scanner;
import static Manager.WestminsterSkinConsultationManager.printDoctorList;

public class PrintController {
    public static void printMenuOption(){
        printDoctorList();
        quesPrintMenu();
    }
    private static void quesPrintMenu(){
        Scanner input = new Scanner(System.in);
        System.out.print("\t\t Continue with main menu (Y/n) ?");

        if(input.next().equals("Y")){
            Design.mainMenu();
        }else {
            System.exit(0);
        }
    }
}
