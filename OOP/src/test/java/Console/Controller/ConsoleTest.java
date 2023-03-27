package Console.Controller;

import Console.Exception.*;
import Model.Doctor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;

import static Console.Controller.AddController.*;
import static Manager.WestminsterSkinConsultationManager.*;
import static Manager.WestminsterSkinConsultationManager.addDoctor;

class ConsoleTest {
    @Test
    void Test01() {
        System.out.println("""
                Test Case ID 01
                Input: Jack123\s
                Expected Output: LetterChrOnlyException\s
                Description: Name,Surname cannot contain numbers\s""");
        Assertions.assertThrows(LetterChrOnlyException.class,()-> isLettersOnly("Jack123"));
        System.out.println();
    }

    @Test
    void Test02() {
        System.out.println("""
                Test Case ID 02
                Input: Jack@#$\s
                Expected Output: LetterChrOnlyException\s
                Description: Name,Surname cannot contain symbols\s""");
        Assertions.assertThrows(LetterChrOnlyException.class,()-> isLettersOnly("Jack@#$"));
        System.out.println();
    }

    @Test
    void Test03() {
        System.out.println("""
                Test Case ID 03
                Input: 2001asd\s
                Expected Output: NumberChrOnlyException\s
                Description: Year, Month, Date must have numbers Only\s""");
        Assertions.assertThrows(NumberChrOnlyException.class,()-> isNumbersOnly("2001asd"));
        System.out.println();
    }

    @Test
    void Test04() {
        System.out.println("""
                Test Case ID 04
                Input: 2001-123-11\s
                Expected Output: DateTimeException\s
                Description: Verify valid month and day.\s""");
        Assertions.assertThrows(DateTimeException.class,()-> createDateOfBirth(2001,123, 11));
        System.out.println();
    }

    @Test
    void Test05() {
        System.out.println("""
                Test Case ID 05
                Input: Future Date\s
                Expected Output: FutureDateException\s
                Description: Doctor BirthDay Cannot be Future.\s""");
        Assertions.assertThrows(FutureDateException.class,()-> isDateInRange(LocalDate.now().plusDays(5)));
        System.out.println();
    }

    @Test
    void Test06() {
        System.out.println("""
                Test Case ID 06
                Input: 18 < Age < 65\s
                Expected Output: AgeException\s
                Description: Doctor Doctor age must be 18 to 60.\s""");
        //Age - 05
        Assertions.assertThrows(AgeException.class,()-> isDateInRange(LocalDate.now().minusYears(5)));

        //Age - 70
        Assertions.assertThrows(AgeException.class,()-> isDateInRange(LocalDate.now().minusYears(70)));
        System.out.println();
    }

    @Test
    void Test07() {
        System.out.println("""
                Test Case ID 07
                Input: 12345sdf@3\s
                Expected Output: NumberChrOnlyException\s
                Description: Mobile number must have numbers only.\s""");

        Assertions.assertThrows(NumberChrOnlyException.class,()-> isNumbersOnly("12345sdf@3"));
        System.out.println();
    }

    @Test
    void Test08() {
        System.out.println("""
                Test Case ID 08
                Input: Add duplicate medical lc numbers\s
                Expected Output: DoctorExistException\s
                Description: Mobile number must have numbers only.\s""");
        Doctor doctor1 = new Doctor("Ganindu","Pushpakumara",LocalDate.of(2001,6,11),
                "1234455","SP001","Medical");
        Doctor doctor2 = new Doctor("Ranindu","Gunasekara",LocalDate.of(1996,5,12),
                "3454455","SP001","Cosmetic");
        Assertions.assertThrows(DoctorExistException.class,()->{
            addDoctor(doctor1);
            addDoctor(doctor2);
        });
        System.out.println();
    }

    @Test
    void Test09() {
        System.out.println("""
                Test Case ID 09
                Input: Delete null object\s
                Expected Output: NullPointException\s
                Description: Cannot delete a doctor not at the centre.\s""");
        Assertions.assertThrows(NullPointerException.class,()-> deleteDoctor(""));
        System.out.println();
    }

    @Test
    void Test10() {
        System.out.println("""
                Test Case ID 10
                Input: Delete null object\s
                Expected Output: NullPointException\s
                Description: Cannot load when file is not found.\s""");
        Assertions.assertThrows(IOException.class,()-> loadFile("" ));
        System.out.println();
    }

    @Test
    void Test11() {
        System.out.println("""
                Test Case ID 11
                Input: Adding 11th Doctor obj\s
                Expected Output: CentreIsFullException\s
                Description: Centre can only add 10 Doctors.\s""");


        Assertions.assertThrows(CentreIsFullException.class,()-> {
            addDoctor(new Doctor("Ranindu","Gunasekara",LocalDate.of(1996,5,12),
                    "3454455","1","Cosmetic"));
            addDoctor(new Doctor("Ranindu","Gunasekara",LocalDate.of(1996,5,12),
                    "3454455","2","Cosmetic"));
            addDoctor(new Doctor("Ranindu","Gunasekara",LocalDate.of(1996,5,12),
                    "3454455","3","Cosmetic"));
            addDoctor(new Doctor("Ranindu","Gunasekara",LocalDate.of(1996,5,12),
                    "3454455","4","Cosmetic"));
            addDoctor(new Doctor("Ranindu","Gunasekara",LocalDate.of(1996,5,12),
                    "3454455","5","Cosmetic"));
            addDoctor(new Doctor("Ranindu","Gunasekara",LocalDate.of(1996,5,12),
                    "3454455","6","Cosmetic"));
            addDoctor(new Doctor("Ranindu","Gunasekara",LocalDate.of(1996,5,12),
                    "3454455","7","Cosmetic"));
            addDoctor(new Doctor("Ranindu","Gunasekara",LocalDate.of(1996,5,12),
                    "3454455","8","Cosmetic"));
            addDoctor(new Doctor("Ranindu","Gunasekara",LocalDate.of(1996,5,12),
                    "3454455","9","Cosmetic"));
            addDoctor(new Doctor("Ranindu","Gunasekara",LocalDate.of(1996,5,12),
                    "3454455","10","Cosmetic"));
            addDoctor(new Doctor("Ranindu","Gunasekara",LocalDate.of(1996,5,12),
                    "3454455","11","Cosmetic"));

        });
    }
}