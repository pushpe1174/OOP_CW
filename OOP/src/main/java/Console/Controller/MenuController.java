package Console.Controller;

import Console.View.Design;
import GUI.View.MainFoam;

import java.util.InputMismatchException;
import java.util.Scanner;

import static Console.View.Design.mainMenu;
import static GUI.View.MainFoam.getMainFoam;

public class MenuController {
    public static void menuOption(){
        do{
            System.out.print("\t\t Select Option - > ");
        }while (menuException());
    }

    private static boolean menuException(){
        Scanner input = new Scanner(System.in);
        int option;
        try {
            option = input.nextInt();
            menuAction(option);

            // String input Exception Handling
        }catch (InputMismatchException e){
            System.out.println("\t\t * Invalid input \n");
            return true;

            //Option range Exception Handling
        }catch (IllegalStateException e){
            System.out.println("\t\t * Select correct option \n");
            return true;
        }
        return false;
    }

    private static void menuAction(int option){
        switch (option){
            case 1  -> Design.addMenu();
            case 2  -> Design.deleteMenu();
            case 3  -> Design.printMenu();
            case 4  -> SaveController.saveMenu();
            case 5  -> LoadController.loadMenu();
            case 6  -> {
                getMainFoam();
                mainMenu();
            }
            case 7  -> System.exit(0);
            default -> throw new IllegalStateException();
        }
    }
}
