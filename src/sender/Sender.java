package sender;

import crypto.*;
import java.io.IOException;
import java.math.BigInteger;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Sender {
    public Sender () {
        PublicKey publicKey = loadReceiverPublicKey();
        try {
            while (true) {
                String message = readMessageFromConsole();
                byte[] encryptedMessage = RSA.encrypt(message, publicKey);
                saveEncryptedMessageToFile(encryptedMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String readMessageFromConsole() {
        System.out.println("Enter message to encrypt: ");
        try (Scanner scanner = new Scanner(System.in)) {
            return scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static PublicKey loadReceiverPublicKey() {
        Path filePath = Paths.get("../publicKey.txt");
        try {
            BigInteger n = new BigInteger(Files.readAllLines(filePath).get(1));
            BigInteger e = new BigInteger(Files.readAllLines(filePath).get(2));
            return new PublicKey(n, e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void saveEncryptedMessageToFile(byte[] encryptedMessage) {
        try (FileOutputStream fos = new FileOutputStream("../encryptedMessage.txt")) {
            fos.write(encryptedMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
