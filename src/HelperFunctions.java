import java.math.BigInteger;

public class HelperFunctions {
    public static BigInteger gcd(BigInteger a, BigInteger b)  {
        if (b.compareTo(BigInteger.ZERO) == 0) return a;
        return gcd(b, a.mod(b));
    }

    public static BigInteger mpow(BigInteger a, BigInteger b, BigInteger m) {
        BigInteger res = BigInteger.ONE;
        while(b.compareTo(BigInteger.ZERO) > 0) {
            if (b.mod(BigInteger.TWO).compareTo(BigInteger.ZERO) > 0) {
                res = res.multiply(a);
                res = res.mod(m);
            }
            a = a.multiply(a);
            a = a.mod(m);
            b = b.divide(BigInteger.TWO);
        }
        return res;
    }

    public static BigInteger modInverse(BigInteger a, BigInteger mod) {
        return mpow(a, mod.subtract(BigInteger.TWO), mod);
    }
}
