package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Availability implements SavableObject {
    private Doctor doctor;
    private LocalDate date;
    private LocalTime startTime;


    public Availability() {
    }
    public Availability(Doctor doctor, LocalDate date, LocalTime startTime) {
        this.doctor = doctor;
        this.date = date;
        this.startTime = startTime;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Availability that = (Availability) o;
        return Objects.equals(doctor, that.doctor) && Objects.equals(date, that.date) && Objects.equals(startTime, that.startTime);
    }
}
