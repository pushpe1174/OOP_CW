package GUI.Controller;

import GUI.View.ConsultationFoam;
import Manager.GuiConsultationManager;
import Model.Availability;
import Model.Consultation;
import Model.Patient;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

import static Manager.GuiConsultationManager.*;
import static Manager.WestminsterSkinConsultationManager.getConsoleDoctorList;

public class ConsultationController {
    private final ConsultationFoam consultationFoam;
    private Availability availableDoctor;
    private Patient selectedPatient;

    private String duration;
    public ConsultationController(){
        consultationFoam = new ConsultationFoam();
        setDoctorList();
        consultationFoam.getBtnDocCheck().addActionListener(
                e -> {
                    if(checkDoctorField()){
                        JOptionPane.showMessageDialog(null,
                                "Required all Check Doctor Fields","Error",
                                JOptionPane.ERROR_MESSAGE);
                    }else if(timeRangeCheck()){
                        JOptionPane.showMessageDialog(null,
                                "Check the Time range ","Error",
                                JOptionPane.ERROR_MESSAGE);
                    }else{
                        setDoctorDetails();
                    }
                }
        );
        consultationFoam.getBtnSave().addActionListener(
                e -> {
                    if(checkDoctor()){
                        JOptionPane.showMessageDialog(null,
                                "First Check Doctor Availability ","Error",
                                JOptionPane.ERROR_MESSAGE);
                    }else if(selectedPatient == null){
                        JOptionPane.showMessageDialog(null,
                                "Add Patient Details","Error",
                                JOptionPane.ERROR_MESSAGE);
                    } else if (checkDuration()) {
                        JOptionPane.showMessageDialog(null,
                                "Add Duration ","Error",
                                JOptionPane.ERROR_MESSAGE);
                    }else if(checkFileFormat()){
                        JOptionPane.showMessageDialog(null,
                                "Image Format: JPEG or JPG","Error",
                                JOptionPane.ERROR_MESSAGE);
                    } else{
                        setCosultation();
                        setDefault();
                        setDefaultDoctorDetails();
                        cleanDoctorDetails();
                        cleanAppointmentDetails();
                    }
                }
        );

        consultationFoam.getBtnPatientCheck().addActionListener(
                e -> {
                    if(isNicEmpty()){
                        JOptionPane.showMessageDialog(null,
                                "Fill Nic Number","Error",
                                JOptionPane.ERROR_MESSAGE);
                    } else if(!checkIsNicInt()){
                        JOptionPane.showMessageDialog(null,
                                "NIC : Number Format Expected","Error",
                                JOptionPane.ERROR_MESSAGE);
                    }else if(checkPatient()){
                        JOptionPane.showMessageDialog(null,"Patient found",
                                "Success",JOptionPane.INFORMATION_MESSAGE);
                        setFoundPatient();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Patient not Found",
                                "Success",JOptionPane.ERROR_MESSAGE);
                        unlockPatientFields();
                    }
                }
        );

        consultationFoam.getBtnCreate().addActionListener(
                e -> {
                    if(consultationFoam.getTxtPatientNic().getText().equals("")){
                        JOptionPane.showMessageDialog(null,
                                "First Check Patient","Error",
                                JOptionPane.ERROR_MESSAGE);
                    }else if(isEmpty()){
                        JOptionPane.showMessageDialog(null,
                                "Required All Patient details","Error",
                                JOptionPane.ERROR_MESSAGE);
                    }else if(!checkIsMobileInt()){
                        JOptionPane.showMessageDialog(null,
                                "Mobile : Number Format Expected","Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else if(selectedPatient != null){
                        JOptionPane.showMessageDialog(null,
                                "Patient Already exist","Error",
                                JOptionPane.ERROR_MESSAGE);
                    } else{
                        setNewPatient();
                        lockPatientFields();
                    }
                }
        );

        consultationFoam.getBtnReset().addActionListener(e -> {
            if(consultationFoam.getTxtPatientNic().getText().equals("")){
                JOptionPane.showMessageDialog(null,"Nothing to reset",
                        "Error",JOptionPane.ERROR_MESSAGE);
            }else{
                setDefault();
            }
        });

        consultationFoam.getBtnCheckFile().addActionListener(
                e->{
                    JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Format",
                            "png","jpg","jpeg"));
                    fileChooser.setAcceptAllFileFilterUsed(false);
                    int saveAll = fileChooser.showSaveDialog(null);
                    // if the user selects a file
                    if (saveAll == JFileChooser.APPROVE_OPTION)
                        consultationFoam.getFileName().setText(fileChooser.getSelectedFile().getAbsolutePath());

                    else
                        consultationFoam.getFileName().setText("");
                }
        );

    }

    private boolean checkFileFormat() {
        String filename = consultationFoam.getFileName().getText();
        if(filename.equals(""))
            return false;

        return !filename.endsWith("jpg") && !filename.endsWith("jpeg");
    }

    private void setDefaultDoctorDetails() {
       consultationFoam.getComboBoxDocList().setSelectedItem("");
       consultationFoam.getStartTime().clear();
       consultationFoam.getEndTime().clear();
       consultationFoam.getDateChooser().setDate(null);
    }

    private void setCosultation() {
        Consultation consultation = new Consultation();
        consultation.setAvailability(availableDoctor);
        consultation.setPatient(selectedPatient);

        String min = Objects.requireNonNull(consultationFoam.getComboBoxDuration().getSelectedItem()).toString();
        LocalTime sessionStart = getSessionStart();
        consultation.setSessionStart(sessionStart);
        consultation.setSessionEnd(sessionStart.plusMinutes(Long.parseLong(String.valueOf(min))));

        consultation.setFee(calculateFee());
        consultation.setDuration(duration);


        if(checkConsultation(consultation)){
            JOptionPane.showMessageDialog(null,
                    "Patient Cannot appoint Same doctor Same Date","Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
            String sessionTime = timeFormatter.format(getSessionStart());
            encryptDate(consultation);
            JOptionPane.showMessageDialog(null,
                    "Session start - " + sessionTime +"\n" +
                            "Session fee   - "  + "€"+calculateFee() + "\n" +
                            "Session duration - " + duration  +"min"
                    ,"Success",
                    JOptionPane.INFORMATION_MESSAGE);
            GuiConsultationManager.addConsultationList(consultation);
        }
    }


    private void encryptDate(Consultation consultation){
        //Note
        consultation.setNote(consultationFoam.getPatientNotes().getText());

        //Source Path
        String source = consultationFoam.getFileName().getText();
        String id = consultation.getPatient().getUniqueId();
        String doctorId = consultation.getAvailability().getDoctor().getMedicalLc();
        int year =  consultation.getAvailability().getDate().getYear();
        int mon =  consultation.getAvailability().getDate().getMonthValue();
        int day = consultation.getAvailability().getDate().getDayOfMonth();
        String filename = id + "_" + doctorId + "_" + year + "_" + mon + "_" + day;

        consultation.setSourcePath(source,filename);
    }
    private boolean isNicEmpty() {
        return consultationFoam.getTxtPatientNic().getText().equals("");
    }

    private void setDefault(){
        consultationFoam.getTxtPatientNic().setEditable(true);
        consultationFoam.getTxtPatientNic().setText("");

        consultationFoam.getTxtPatientName().setEditable(false);
        consultationFoam.getTxtPatientName().setBackground(new Color(241, 242, 246));
        consultationFoam.getTxtPatientName().setText("");

        consultationFoam.getTxtPatientSurname().setEditable(false);
        consultationFoam.getTxtPatientSurname().setBackground(new Color(241, 242, 246));
        consultationFoam.getTxtPatientSurname().setText("");

        consultationFoam.getBirthDateChooser().getCalendarButton().setEnabled(false);
        consultationFoam.getBirthDateChooser().getDateEditor().setEnabled(false);
        consultationFoam.getBirthDateChooser().getDateEditor().setDate(null);


        consultationFoam.getTxtPatientMobile().setEditable(false);
        consultationFoam.getTxtPatientMobile().setBackground(new Color(241, 242, 246));
        consultationFoam.getTxtPatientMobile().setText("");

        consultationFoam.getRdBtnMale().setEnabled(false);
        consultationFoam.getRdBtnFemale().setEnabled(false);
        consultationFoam.getGender().clearSelection();

        consultationFoam.getComboBoxDuration().setSelectedItem("");

        selectedPatient = null;
    }
    private boolean checkIsMobileInt(){
        String mobile = consultationFoam.getTxtPatientMobile().getText();
        return  mobile.matches("[0-9]+");
    }

    private boolean checkIsNicInt(){
        String nic = consultationFoam.getTxtPatientNic().getText();
        return nic.matches("[0-9]+");
    }
    private boolean isEmpty() {
        if(consultationFoam.getTxtPatientName().getText().equals(""))
            return true;

        if(consultationFoam.getTxtPatientSurname().getText().equals(""))
            return true;

        if(consultationFoam.getBirthDateChooser().getDate() == null)
            return true;

        if(consultationFoam.getTxtPatientMobile().getText().equals(""))
            return true;

        return !(consultationFoam.getRdBtnMale().isSelected() || consultationFoam.getRdBtnFemale().isSelected());
    }
    private void setNewPatient() {
        selectedPatient = new Patient();
        selectedPatient.setUniqueId(consultationFoam.getTxtPatientNic().getText());
        selectedPatient.setName(consultationFoam.getTxtPatientName().getText());
        selectedPatient.setSurname(consultationFoam.getTxtPatientSurname().getText());

        selectedPatient.setMobile(consultationFoam.getTxtPatientMobile().getText());
        if(consultationFoam.getRdBtnMale().isSelected()){
            selectedPatient.setGender("Male");
        }else{
            selectedPatient.setGender("Female");
        }
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy,MM,dd");
        String birthDate = dataFormat.format(consultationFoam.getBirthDateChooser().getDate());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy,MM,dd");
        LocalDate localDate = LocalDate.parse(birthDate,dateTimeFormatter);
        selectedPatient.setBirthDate(localDate);

        if(checkAddPatient(selectedPatient)){
            JOptionPane.showMessageDialog(null,"Patient Already Exist","Error",
                    JOptionPane.ERROR_MESSAGE);
        }else{
            addPatientList(selectedPatient);
            JOptionPane.showMessageDialog(null,"Patient Successfully Added","Success",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void patientFields(boolean flag , Color color) {
        consultationFoam.getTxtPatientName().setEditable(flag);
        consultationFoam.getTxtPatientName().setBackground(color);

        consultationFoam.getTxtPatientSurname().setEditable(flag);
        consultationFoam.getTxtPatientSurname().setBackground(color);

        consultationFoam.getBirthDateChooser().getCalendarButton().setEnabled(flag);

        consultationFoam.getTxtPatientMobile().setEditable(flag);
        consultationFoam.getTxtPatientMobile().setBackground(color);

        consultationFoam.getRdBtnMale().setEnabled(flag);
        consultationFoam.getRdBtnFemale().setEnabled(flag);
    }
    private void lockPatientFields() {
        patientFields(false,new Color(200, 251, 198));
    }


    private void unlockPatientFields() {
        consultationFoam.getTxtPatientNic().setEditable(false);
        patientFields(true,new Color(241, 242, 246));
    }

    private boolean checkPatient() {
        ArrayList<Patient> patients = GuiConsultationManager.getPatientList();
        for (Patient patient:patients) {
            if(consultationFoam.getTxtPatientNic().getText().equals(patient.getUniqueId())){
                selectedPatient = patient;
                return true;
            }
        }
        return false;
    }

    private void setFoundPatient(){
        consultationFoam.getTxtPatientNic().setEditable(false);
        consultationFoam.getTxtPatientName().setText(selectedPatient.getName());
        consultationFoam.getTxtPatientName().setBackground(new Color(200, 251, 198));
        consultationFoam.getTxtPatientSurname().setText(selectedPatient.getSurname());
        consultationFoam.getTxtPatientSurname().setBackground(new Color(200, 251, 198));

        LocalDate date = selectedPatient.getBirthDate();
        int mon = date.getMonthValue() - 1;
        int day = date.getDayOfMonth();
        int year = date.getYear() - 1900;

        consultationFoam.getBirthDateChooser().getDateEditor().setDate(new Date(year,mon,day));
        consultationFoam.getBirthDateChooser().getCalendarButton().setEnabled(false);

        consultationFoam.getTxtPatientMobile().setText(selectedPatient.getMobile());
        consultationFoam.getTxtPatientMobile().setBackground(new Color(200, 251, 198));

        if(selectedPatient.getGender().equals("Male"))
            consultationFoam.getRdBtnMale().setSelected(true);
        else
            consultationFoam.getRdBtnFemale().setSelected(true);
    }

    private Availability getDoctor() {
        ArrayList<Availability> copyDoctorList = new ArrayList<>(getAvailableDoctorList());
        for (Availability doctor: copyDoctorList) {
            if(doctor.getDoctor().getName().equals(Objects.requireNonNull(consultationFoam.getComboBoxDocList()
                    .getSelectedItem()).toString())){
                if(checkTime(doctor.getStartTime()) && checkDate(doctor.getDate()))
                    return doctor;
            }
        }
        return null;
    }

    private boolean checkTime(LocalTime startTime){
        LocalTime customerStartTime = consultationFoam.getStartTime().getTime();
        LocalTime customerEndTime = consultationFoam.getEndTime().getTime();

        return (customerStartTime.equals(startTime) || startTime.isAfter(customerStartTime))
                && customerEndTime.isAfter(startTime);
    }


    private boolean checkDate(LocalDate date) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy,MM,dd");
        String birthDate = dataFormat.format(consultationFoam.getDateChooser().getDate());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy,MM,dd");
        LocalDate localDate = LocalDate.parse(birthDate,dateTimeFormatter);

        return localDate.equals(date);
    }


    private void setDoctorDetails() {
        if (getDoctor() == null){
            int setDoctor = JOptionPane.showConfirmDialog(null,
                    "Doctor not found. Do you need to set a another doctor ?", "Doctor",
                    JOptionPane.YES_NO_OPTION);

            if (setDoctor == JOptionPane.NO_OPTION) {
                cleanDoctorDetails();
            } else if(setDoctor == JOptionPane.YES_OPTION) {
                if(checkRandomDoctor()){
                    JOptionPane.showMessageDialog(null,
                            "No doctor found ","Error",
                            JOptionPane.ERROR_MESSAGE);
                    cleanDoctorDetails();
                }else{
                    setDoctorLabels(setRandomDoctor());
                }
            }

        }else{
            JOptionPane.showMessageDialog(null,"Doctor found",
                    "Success",JOptionPane.INFORMATION_MESSAGE);
            setDoctorLabels(getDoctor());

        }
    }

    private void cleanAppointmentDetails(){
        consultationFoam.getComboBoxDuration().setSelectedItem(null);
        consultationFoam.getFileName().setText("");
        consultationFoam.getPatientNotes().setText("");
    }

    private void cleanDoctorDetails() {
        //Doctor Name
        consultationFoam.getTxtDocName().setText("");
        consultationFoam.getTxtDocName().setBackground(new Color(241, 242, 246));


        //Doctor Surname
        consultationFoam.getTxtDocSurname().setText("");
        consultationFoam.getTxtDocSurname().setBackground(new Color(241, 242, 246));

        //Doctor Available Time
        consultationFoam.getTxtDocAvailability().setText("");
        consultationFoam.getTxtDocAvailability().setBackground(new Color(241, 242, 246));

        //Doctor Spec
        consultationFoam.getTxtDocSpec().setText("");
        consultationFoam.getTxtDocSpec().setBackground(new Color(241, 242, 246));
    }

    private Availability setRandomDoctor(){
        ArrayList<Availability> selectedDoctorList = getRandomDoctorList();
        Random random = new Random();
        int index = random.nextInt(selectedDoctorList.size());
        availableDoctor = selectedDoctorList.get(index);
        return availableDoctor;
    }

    private boolean checkRandomDoctor() {
        return getRandomDoctorList().isEmpty();
    }

    private ArrayList<Availability> getRandomDoctorList() {
        ArrayList<Availability> copyDoctorList = new ArrayList<>(getAvailableDoctorList());
        ArrayList<Availability> selectedDoctorList  = new ArrayList<>();
        for (Availability doctor: copyDoctorList) {
                if(checkTime(doctor.getStartTime()) && checkDate(doctor.getDate())){
                    selectedDoctorList.add(doctor);
            }
        }
        return selectedDoctorList;

    }

    private void setDoctorLabels(Availability availableDoctor){
        //Doctor Name
        consultationFoam.getTxtDocName().setText(availableDoctor.getDoctor().getName());
        consultationFoam.getTxtDocName().setBackground(new Color(200, 251, 198));

        //Doctor Surname
        consultationFoam.getTxtDocSurname().setText(availableDoctor.getDoctor().getSurname());
        consultationFoam.getTxtDocSurname().setBackground(new Color(200, 251, 198));

        //Doctor Available Time
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        String startTime = timeFormatter.format(availableDoctor.getStartTime());
        consultationFoam.getTxtDocAvailability().setText(startTime);
        consultationFoam.getTxtDocAvailability().setBackground(new Color(200, 251, 198));

        //Doctor Spec
        consultationFoam.getTxtDocSpec().setText(availableDoctor.getDoctor().getSpec());
        consultationFoam.getTxtDocSpec().setBackground(new Color(200, 251, 198));

        this.availableDoctor =availableDoctor;
    }

    //Check Doctor
    private boolean checkDoctor() {
        return consultationFoam.getTxtDocName().getText().equals("");
    }

    //Set Doctors for Combo Box
    private void setDoctorList(){
        consultationFoam.getComboBoxDocList().addItem("");
        getConsoleDoctorList().forEach(doctor -> consultationFoam.getComboBoxDocList().addItem(doctor.getName()));
    }

    //Check Doctor Field
    private boolean checkDoctorField() {
        if (Objects.requireNonNull(consultationFoam.getComboBoxDocList().getSelectedItem()).toString().equals(""))
            return true;
        if (consultationFoam.getStartTime().getTime() == null|| consultationFoam.getEndTime().getTime() == null)
            return true;
        return consultationFoam.getDateChooser().getDate() == null;
    }

    //Check Time Range
    private boolean timeRangeCheck(){
        return consultationFoam.getEndTime().getTime().isBefore(consultationFoam.getStartTime().getTime()) ||
                consultationFoam.getEndTime().getTime().equals(consultationFoam.getStartTime().getTime());
    }


    //Check Duration
    private boolean checkDuration(){
        String selectDuration = Objects.requireNonNull(consultationFoam.getComboBoxDuration().
                getSelectedItem()).toString();
        if(selectDuration.equals("")){
            return true;
        }
        duration = selectDuration;
        return false;
    }

    //Calculate Fee
    private String calculateFee(){
        DecimalFormat patternFee =new DecimalFormat("##.00");
        double appointTime = Double.parseDouble(duration);
        double fee;
        if(isPatientThere(selectedPatient.getUniqueId())){
                fee = Double.parseDouble(patternFee.format((appointTime/60.0)* 25));
                return String.valueOf(fee);
        }
        fee = Double.parseDouble(patternFee.format((appointTime/60.0)* 15));
        return String.valueOf(fee);
    }

    //Get Session time
    private LocalTime getSessionStart() {

        LocalTime localEndTime = getLastAppointmentTime(availableDoctor.getDoctor().getMedicalLc()
                ,availableDoctor.getDate());

        return localEndTime == null ? availableDoctor.getStartTime() : localEndTime;
    }
}
