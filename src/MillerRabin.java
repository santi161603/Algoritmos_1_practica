import java.math.BigInteger;
import java.util.Random;

/*
 * Prueba de primalidad de Miller-Rabin
 * Algoritmo probabilístico basado en exponenciación modular.
 * Usa la factorización de n-1 en potencias de 2 y comprueba condiciones
 * para descartar compuestos. Muy rápido y común en criptografía.
 */

public class MillerRabin {
    public static boolean esPrimo(long n, int iteraciones) {
        if (n < 2) return false;
        if (n != 2 && n % 2 == 0) return false;

        long d = n - 1;
        while (d % 2 == 0) d /= 2;

        Random rand = new Random();
        for (int i = 0; i < iteraciones; i++) {
            long a = Math.abs(rand.nextLong()) % (n - 2) + 2;
            long temp = d;
            BigInteger bigA = BigInteger.valueOf(a);
            BigInteger bigN = BigInteger.valueOf(n);
            BigInteger x = bigA.modPow(BigInteger.valueOf(temp), bigN);

            if (x.equals(BigInteger.ONE) || x.equals(bigN.subtract(BigInteger.ONE))) continue;

            while (temp != n - 1) {
                x = x.multiply(x).mod(bigN);
                temp *= 2;
                if (x.equals(BigInteger.ONE)) return false;
                if (x.equals(bigN.subtract(BigInteger.ONE))) break;
            }
            if (!x.equals(bigN.subtract(BigInteger.ONE))) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(esPrimo(137, 5));
    }
}