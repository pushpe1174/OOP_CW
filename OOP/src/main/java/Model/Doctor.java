package Model;

import java.time.LocalDate;

public class Doctor extends Person{

    //initialize variables
    private String medicalLc;
    private String spec;

    public Doctor(){}

    public Doctor(String name, String surname, LocalDate birthDate, String mobile, String medicalLc, String spec) {
        super(name, surname, birthDate, mobile);
        this.medicalLc = medicalLc;
        this.spec = spec;
    }

    public String getMedicalLc() {
        return medicalLc;
    }

    public void setMedicalLc(String medicalLc) {
        this.medicalLc = medicalLc;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    @Override
    public String toString() {
        return super.toString() + "\t\t Specialization = " + spec;
    }
}