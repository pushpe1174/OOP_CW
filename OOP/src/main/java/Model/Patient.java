package Model;

import java.time.LocalDate;

public class Patient extends Person{
    private String uniqueId;
    private String gender;

    public Patient() {}



    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
