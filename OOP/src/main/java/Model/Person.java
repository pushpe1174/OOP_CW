package Model;

import java.time.LocalDate;

public class Person implements SavableObject {
    //    name, surname, date of birth and mobile number
    private String name;
    private String surname;
    private LocalDate birthDate;

    private String mobile;

    public Person() {}

    public Person(String name, String surname, LocalDate birthDate, String mobile) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return
                "\t\t Name      = " + name + "\n" +
                "\t\t Surname   = " + surname + "\n" +
                "\t\t BirthDate = " + birthDate + "\n" +
                "\t\t Mobile    = " + mobile + "\n";
    }
}
