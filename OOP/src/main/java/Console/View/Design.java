package Console.View;

import Console.Controller.AddController;
import Console.Controller.DeleteController;
import Console.Controller.MenuController;
import Console.Controller.PrintController;

public class Design {
    public static void mainMenu(){
        System.out.println("\t\t+--------------------------------------------------------+");
        System.out.println("\t\t|             * Skin Consultation Manager *              |");
        System.out.println("\t\t+--------------------------------------------------------+");

        System.out.println("\t\t|   1.   Add New Doctor                                  |");
        System.out.println("\t\t|   2.   Delete Doctor                                   |");
        System.out.println("\t\t|   3.   Print All                                       |");
        System.out.println("\t\t|   4.   Save to File                                    |");
        System.out.println("\t\t|   5.   Load Data                                       |");
        System.out.println("\t\t|   6.   Switch to GUI                                   |");
        System.out.println("\t\t|   7.   Exit                                            |");

        System.out.println("\t\t+--------------------------------------------------------+");
        MenuController.menuOption();
    }

    public static void addMenu() {
        System.out.println();
        System.out.println("\t\t+--------------------------------------------------------+");
        System.out.println("\t\t|                   * Add New Doctor *                   |");
        System.out.println("\t\t+--------------------------------------------------------+");
        AddController.addMenuOption();
    }

    public static void deleteMenu() {
        System.out.println();
        System.out.println("\t\t+--------------------------------------------------------+");
        System.out.println("\t\t|                    * Delete Doctor *                   |");
        System.out.println("\t\t+--------------------------------------------------------+");
        DeleteController.deleteMenuOption();
    }

    public static void printMenu() {
        System.out.println();
        System.out.println("\t\t+--------------------------------------------------------+");
        System.out.println("\t\t|                 * Print Doctor List *                  |");
        System.out.println("\t\t+--------------------------------------------------------+");
        PrintController.printMenuOption();
    }

}
