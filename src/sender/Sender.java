package sender;

import crypto.*;
import java.io.IOException;
import java.math.BigInteger;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class Sender {
    public static void main(String[] args) {
        String message = "Hello, Receiver! This is a secret message.";
        System.out.println("Original message: " + message);

        // Load the receiver's public key
        PublicKey receiverPublicKey = loadReceiverPublicKey();

        // Encrypt the message using the receiver's public key
        byte[] encryptedMessage = encrypt(message.getBytes(StandardCharsets.UTF_8), receiverPublicKey);

        // Save the encrypted message to a file
        saveEncryptedMessageToFile(encryptedMessage);
        
        System.out.println("Encrypted message has been sent to the receiver.");
    }

    // Load the receiver's public key from a file (simulate this step)
    public static PublicKey loadReceiverPublicKey() {
        // Simulated method
        BigInteger n = new BigInteger("123456789"); // Replace with actual values
        BigInteger e = new BigInteger("65537");     // Replace with actual values
        return new PublicKey(n, e);
    }

    public static byte[] encrypt(byte[] message, PublicKey publicKey) {
        // Simulated encryption
        BigInteger m = new BigInteger(message);
        BigInteger n = publicKey.getN();
        BigInteger e = publicKey.getE();

        BigInteger encrypted = m.modPow(e, n);
        return encrypted.toByteArray();
    }

    public static void saveEncryptedMessageToFile(byte[] encryptedMessage) {
        try (FileOutputStream fos = new FileOutputStream("../encryptedMessage.txt")) {
            fos.write(encryptedMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
