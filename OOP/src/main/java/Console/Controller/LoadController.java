package Console.Controller;

import Console.View.Design;

import java.io.IOException;
import java.util.Scanner;

import static Manager.WestminsterSkinConsultationManager.loadFile;

public class LoadController {
    public static void loadMenu() {
        try {
            loadFile("text.txt" );
        }catch (IOException e){
            System.out.println("\t\t Error: File not found");
        }

        quesLoadMenu();
    }

    private static void quesLoadMenu() {
        Scanner input = new Scanner(System.in);
        System.out.print("\t\t Continue with main menu (Y/n) ?");

        if (input.next().equals("Y")) {
            Design.mainMenu();
        } else {
            System.exit(0);
        }
    }

}
