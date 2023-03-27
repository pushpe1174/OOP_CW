package GUI.Controller;

import Cryptography.ImageCrypto;
import Cryptography.NoteCrypto;
import GUI.View.PatientData;
import Model.Consultation;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PatientDataController {

    private final PatientData patientData;
    public PatientDataController(Consultation consultationObject){
        patientData = new PatientData();
        patientData.getTextArea().setText(consultationObject.getNote());
        patientData.getBtnDecrypt().addActionListener(
                e -> decryptNote(consultationObject.getNote())
        );
        patientData.getBtnOpen().addActionListener(
                e -> decryptImage(consultationObject.getSourcePath())
        );
    }

    private void decryptNote(String note){
        if (note.equals("")) {
            JOptionPane.showMessageDialog(null,"Nothing to Display","Error"
                    ,JOptionPane.ERROR_MESSAGE);
        }else{
            patientData.getTextArea().setText(NoteCrypto.decrypt(note));
        }
    }
    private void decryptImage(String sourcePath) {
        if (sourcePath.equals("")) {
            JOptionPane.showMessageDialog(null,"Nothing to Display","Error"
                    ,JOptionPane.ERROR_MESSAGE);
        } else {
            ImageCrypto.saveDecryptFile(sourcePath);
            try {
                Desktop.getDesktop().open(new File("src/main/java/Data/decrypt.jpeg"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //delete after 1 s
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            try {
                                Files.delete(Path.of("src/main/java/Data/decrypt.jpeg"));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    },
                    1000
            );
        }
    }
}
