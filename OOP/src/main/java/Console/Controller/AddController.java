package Console.Controller;

import Console.Exception.*;
import Console.View.Design;
import Model.Doctor;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

import static Manager.WestminsterSkinConsultationManager.addDoctor;
import static Manager.WestminsterSkinConsultationManager.isListFull;


public class AddController {
    public static void addMenuOption() {
        try {
            if (isListFull()) {
                Scanner input = new Scanner(System.in);
                System.out.print("\t\t Enter Name      - ");
                String name = input.next();
                isLettersOnly(name);

                System.out.print("\t\t Enter Surname   - ");
                String surname = input.next();
                isLettersOnly(surname);

                System.out.println("\t\t Enter Birth   ->");

                int year = numException("\t\t\t Year    - ");
                int month = numException("\t\t\t Month   - ");
                int day = numException("\t\t\t Day     - ");

                //Creating date variable
                LocalDate birthDate = createDateOfBirth(year, month, day);
                isDateInRange(birthDate);

                System.out.print(("\t\t Enter Mobile    - "));
                String mobile = input.next();
                isNumbersOnly(mobile);

                System.out.print("\t\t Medical license - ");
                String medicalLc = input.next();

                System.out.println("\t\t Select Specialisation  -> ");
                String spec = selectSpec();

                //sending parameters for customer add
                Doctor doctor = new Doctor();
                doctor.setName(name);
                doctor.setSurname(surname);
                doctor.setBirthDate(birthDate);
                doctor.setMobile(mobile);
                doctor.setMedicalLc(medicalLc);
                doctor.setSpec(spec);

                //Print console msg
                System.out.println(addDoctor(doctor));
            }else{
                throw new CentreIsFullException();
            }
        }catch (DateTimeException e){
            System.out.println("\t\t Error : Check Date");
        }catch (Exception e){
            System.out.println("\t\t " + e.getMessage());
        }finally {
            quesAddMenu();
        }
    }

    private static String selectSpec(){
        String dermatology = "Not Given";
        boolean flag;
        do{
            flag = false;
            System.out.println("\t\t\t\t 1 - Cosmetic dermatology ");
            System.out.println("\t\t\t\t 2 - Medical dermatology ");
            System.out.println("\t\t\t\t 3 - Paediatric dermatology ");
            try {
                dermatology = selectSpecException(numException("\t\t\t\t Select ->  "));
            }catch (IllegalStateException e){
                System.out.println("\t\t Select correct option \n");
                flag = true;
            }
        }while (flag);
        return dermatology;
    }

    private static int numException(String enterData){
        Scanner input = new Scanner(System.in);
        int i = 0;
        boolean flag;
        do {
            System.out.print(enterData);
            if(input.hasNextInt()){
                i = input.nextInt();
                flag = true;
            }else{
                input.nextLine();
                flag = false;
                System.out.println("\t\t Error : Support Numbers ONLY");
            }
        }while(!flag);
        return i;
    }

    private static String selectSpecException(int option){
        switch (option) {
            case 1 -> {
                return "Cosmetic";
            }
            case 2 -> {
                return "Medical";
            }
            case 3 -> {
                return "Paediatric";
            }
            default -> throw new IllegalStateException();
        }
    }


    private static void quesAddMenu(){
        Scanner input = new Scanner(System.in);
        System.out.print("\t\t Do you want to add a Doctor(Y/n) ?");

        if(input.next().equals("Y")){
            Design.addMenu();
        }else {
            Design.mainMenu();
        }
    }

    public static void isLettersOnly(String isLetters) throws LetterChrOnlyException {
        if (!isLetters.matches("[a-zA-Z]+")){
            throw new LetterChrOnlyException();
        }
    }

    public static LocalDate createDateOfBirth(int year, int month, int day){
        try {
            return  LocalDate.of(year, month, day);
        }catch (DateTimeException e){
            throw new DateTimeException("");
        }
    }

    public static void isNumbersOnly(String isNumbers) throws NumberChrOnlyException {
        if(!isNumbers.matches("[0-9]+")){
            throw new NumberChrOnlyException();
        }
    }

    public static void isDateInRange(LocalDate birthDate) throws AgeException, FutureDateException {
        int age = LocalDate.now().getYear() - birthDate.getYear();
        if(birthDate.isAfter(LocalDate.now())){
            throw new FutureDateException();
        }else if( age<18 | 60<age ) throw new AgeException();
    }


}
