package crypto;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {
    private PublicKey publicKey;
    private PrivateKey privateKey;

    public RSA (int keyLength) {
        KeyPair keyPair = generateKey(keyLength);
        publicKey = keyPair.getPublicKey();
        privateKey = keyPair.getPrivateKey();
    }

    private static KeyPair generateKey(int keyLength) {
        SecureRandom secureRandom = new SecureRandom();
        BigInteger p = BigInteger.probablePrime(keyLength / 2, secureRandom);
        BigInteger q = BigInteger.probablePrime(keyLength / 2, secureRandom);

        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        BigInteger e = BigInteger.probablePrime(keyLength / 2, secureRandom);
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e = e.add(BigInteger.ONE); // Corrected this line
        }

        BigInteger d = e.modInverse(phi);

        PublicKey publicKey = new PublicKey(n, e);
        PrivateKey privateKey = new PrivateKey(n, d);

        return new KeyPair(publicKey, privateKey);
    }

    public static byte[] encrypt(String message, PublicKey publicKey) {
        BigInteger n = publicKey.getN();
        BigInteger e = publicKey.getE();
        BigInteger m = new BigInteger(message);

        return m.modPow(e, n).toByteArray();
    }

    public byte[] decrypt(byte[] data, PrivateKey privateKey) {
        BigInteger n = privateKey.getN();
        BigInteger d = privateKey.getD();
        BigInteger c = new BigInteger(data);

        return c.modPow(d, n).toByteArray();
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }
}
