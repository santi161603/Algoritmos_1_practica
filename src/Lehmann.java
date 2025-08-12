import java.math.BigInteger;
import java.util.Random;
/*
 * Prueba de primalidad de Lehmann
 * Algoritmo probabilístico que verifica si a^((n-1)/2) ≡ ±1 (mod n).
 * Más seguro que Fermat pero con pequeña probabilidad de error.
 */

public class Lehmann {
    public static boolean esPrimo(long n, int iteraciones) {
        if (n < 2) return false;
        Random rand = new Random();
        for (int i = 0; i < iteraciones; i++) {
            long a = 1 + rand.nextInt((int) (n - 1));
            BigInteger res = BigInteger.valueOf(a)
                    .modPow(BigInteger.valueOf((n - 1) / 2), BigInteger.valueOf(n));
            if (!res.equals(BigInteger.ONE) && !res.equals(BigInteger.valueOf(n - 1)))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(esPrimo(137, 5));
    }
}
