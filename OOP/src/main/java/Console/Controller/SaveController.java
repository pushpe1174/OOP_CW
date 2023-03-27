package Console.Controller;

import Console.View.Design;
import java.util.Scanner;
import static Manager.WestminsterSkinConsultationManager.saveFile;

public class SaveController {

    public static void saveMenu() {
        saveFile();
        quesSaveMenu();
    }

    private static void quesSaveMenu() {
        Scanner input = new Scanner(System.in);
        System.out.print("\t\t Continue with main menu (Y/n) ?");

        if (input.next().equals("Y")) {
            Design.mainMenu();
        } else {
            System.exit(0);
        }
    }
}
