package receiver;

import crypto.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class Receiver {
    public static void main(String[] args) {
        // Load the receiver's private key
        PrivateKey receiverPrivateKey = loadReceiverPrivateKey();

        // Load the encrypted message from the file
        byte[] encryptedMessage = loadEncryptedMessageFromFile();

        // Decrypt the message using the receiver's private key
        byte[] decryptedMessage = decrypt(encryptedMessage, receiverPrivateKey);
        System.out.println("Decrypted message: " + new String(decryptedMessage, StandardCharsets.UTF_8));
    }

    // Load the receiver's private key from a file (simulate this step)
    public static PrivateKey loadReceiverPrivateKey() {
        // Simulated method
        BigInteger n = new BigInteger("123456789"); // Replace with actual values
        BigInteger d = new BigInteger("987654321"); // Replace with actual values
        return new PrivateKey(n, d);
    }

    public static byte[] decrypt(byte[] encryptedMessage, PrivateKey privateKey) {
        // Simulated decryption
        BigInteger c = new BigInteger(encryptedMessage);
        BigInteger n = privateKey.getN();
        BigInteger d = privateKey.getD();

        BigInteger decrypted = c.modPow(d, n);
        return decrypted.toByteArray();
    }

    public static byte[] loadEncryptedMessageFromFile() {
        Path filePath = Paths.get("../encryptedMessage.txt");
        try {
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
