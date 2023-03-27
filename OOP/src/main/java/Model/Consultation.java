package Model;

import Cryptography.ImageCrypto;
import Cryptography.NoteCrypto;

import java.time.LocalTime;

public class Consultation implements SavableObject {
    private Availability availability;
    private Patient patient;
    private LocalTime sessionStart;
    private LocalTime sessionEnd;
    private String duration;

    private String note;
    private String fee;
    private String sourcePath;

    public Consultation() {}

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }


    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalTime getSessionStart() {
        return sessionStart;
    }

    public void setSessionStart(LocalTime sessionStart) {
        this.sessionStart = sessionStart;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        if(note.equals("")){
            this.note = "";
        }else{
            this.note = NoteCrypto.encrypt(note);
        }
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath, String filename) {
        if(!sourcePath.equals("")){
            String path = "src/main/java/Data/";
            this.sourcePath = path + filename + ".pushpe";
            ImageCrypto.saveEncryptFile(sourcePath,filename);
        }else{
            this.sourcePath = "";
        }
    }

    public LocalTime getSessionEnd() {
        return sessionEnd;
    }

    public void setSessionEnd(LocalTime sessionEnd) {
        this.sessionEnd = sessionEnd;
    }

}
