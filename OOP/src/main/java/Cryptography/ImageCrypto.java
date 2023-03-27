package Cryptography;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.*;
import java.security.spec.KeySpec;
import java.util.Objects;

public class ImageCrypto extends JFrame {
    private static final String PASSWORD = "1vG0*B1G$6y8";
    private static final String SALT = "u64Pk@w6m1cd";

    private static final IvParameterSpec ivParameterSpec = new IvParameterSpec(
            new byte[]{'G', 1, 'R', 'Z', 7, 6, 'R', 'C', 'G', 4, 5, 2, 'F', 3, 2, 'U'});

    public static byte[] encrypt(String sourcePath){
        try {
            byte[] fileContent = Files.readAllBytes(Paths.get(sourcePath));
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec keySpec = new PBEKeySpec(PASSWORD.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

            byte[] cipherText = cipher.doFinal(fileContent);

            return ByteBuffer.allocate(16 + 12 + cipherText.length)
                    .put(ivParameterSpec.getIV())
                    .put(SALT.getBytes())
                    .put(cipherText)
                    .array();

        } catch (Exception e) {
            System.out.println("Encrypted Fail");
        }
        return null;
    }

    public static void saveEncryptFile(String sourcePath,String filename){

        String path = "src/main/java/Data/";
        File mainSource = new File(path);
        System.out.println(mainSource.mkdir());
        String fileType = filename + ".pushpe";
        byte[] encryptData = encrypt(sourcePath);
        try{
            Files.write(Paths.get(path + fileType), Objects.requireNonNull(encryptData));
        } catch (IOException e) {
            System.out.println("Nothing to Encrypt");
        }
    }

    public static void saveDecryptFile(String sourcePath){
        byte[] decryptedData = decrypt(sourcePath);

        try {
            ImageIO.read(new ByteArrayInputStream(decryptedData));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // save a file
        Path path = Paths.get("src/main/java/Data/decrypt.jpeg");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decryptedData);
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(byteArrayInputStream);
            ImageIO.write(bufferedImage, "jpg", new File(path.toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static byte[] decrypt(String sourcePath) {
        // read a file
        try {
            byte[] fileContent = Files.readAllBytes(Paths.get(sourcePath));

            // get back the iv and salt that was prefixed in the cipher text
            ByteBuffer byteBuffer = ByteBuffer.wrap(fileContent);

            byteBuffer.get(ivParameterSpec.getIV());
            byteBuffer.get(SALT.getBytes());
            byte[] encryptFullForm = new byte[byteBuffer.remaining()];
            byteBuffer.get(encryptFullForm);

            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec keySpec = new PBEKeySpec(PASSWORD.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            return cipher.doFinal(encryptFullForm);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
