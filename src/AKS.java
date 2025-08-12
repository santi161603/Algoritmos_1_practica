import java.math.BigInteger;

public class AKS {

    // Devuelve true si n = a^b con b >= 2 y a > 1
    public static boolean isPerfectPower(BigInteger n) {
        if (n.compareTo(BigInteger.ONE) <= 0) return false;

        int maxExp = n.bitLength(); // cota: 2^bitLength > n, así que exponente <= bitLength
        for (int b = 2; b <= maxExp; b++) {
            BigInteger low = BigInteger.valueOf(2);
            BigInteger high = rootUpperBound(n, b);

            while (low.compareTo(high) <= 0) {
                BigInteger mid = low.add(high).shiftRight(1); // (low + high) / 2
                BigInteger pow = mid.pow(b);
                int cmp = pow.compareTo(n);
                if (cmp == 0) {
                    return true; // encontramos a^b = n
                } else if (cmp < 0) {
                    low = mid.add(BigInteger.ONE);
                } else {
                    high = mid.subtract(BigInteger.ONE);
                }
            }
        }
        return false;
    }

    // Devuelve una cota superior razonable para la raíz b-ésima de n
    private static BigInteger rootUpperBound(BigInteger n, int b) {
        // aproximación simple: 2^(ceil(bitLength / b))
        int bits = n.bitLength();
        int k = (bits + b - 1) / b; // ceil(bits / b)
        return BigInteger.ONE.shiftLeft(k); // 2^k
    }

    public static void main(String[] args) {
        BigInteger[] tests = {
                new BigInteger("1"),
                new BigInteger("4"),        // 2^2 -> true
                new BigInteger("8"),        // 2^3 -> true
                new BigInteger("9"),        // 3^2 -> true
                new BigInteger("27"),       // 3^3 -> true
                new BigInteger("16"),       // 2^4 -> true
                new BigInteger("17"),       // not perfect power
                new BigInteger("81"),       // 3^4 and 9^2 -> true
                new BigInteger("1048576"),  // 2^20 -> true
                new BigInteger("1048577")   // not
        };

        for (BigInteger t : tests) {
            System.out.printf("%s -> %s%n", t.toString(), isPerfectPower(t) ? "POTENCIA" : "NO POTENCIA");
        }
    }
}
