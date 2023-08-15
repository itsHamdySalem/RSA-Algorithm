package receiver;

import crypto.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;

public class Receiver {
    private static int keySize = 1024;
    private static RSA rsa;

    public Receiver() {
        rsa = new RSA(keySize);
        uploadPublicKey(rsa.getPublicKey());
        PrivateKey privateKey = rsa.getPrivateKey();
        try {
            while (true) {
                byte[] encryptedMessage = loadEncryptedMessageFromFile();
                byte[] decryptedMessage = rsa.decrypt(encryptedMessage, privateKey);
                System.out.println("Decrypted message: " + new String(decryptedMessage, StandardCharsets.UTF_8));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void uploadPublicKey(PublicKey publicKey) {
        Path filePath = Paths.get("../publicKey.txt");
        try {
            Files.write(filePath, publicKey.getN().toString().getBytes());
            Files.write(filePath, publicKey.getE().toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] loadEncryptedMessageFromFile() {
        Path filePath = Paths.get("../encryptedMessage.txt");
        try {
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
