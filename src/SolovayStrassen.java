import java.math.BigInteger;
import java.util.Random;
import java.math.BigInteger;
import java.util.Random;

/*
 * Prueba de primalidad de Solovay-Strassen
 */
public class SolovayStrassen {

    // Calcula el símbolo de Jacobi (a/n), devuelve -1, 0, o 1.
    // Requiere n impar y > 0.
    public static int jacobi(long a, long n) {
        if (n <= 0 || (n % 2) == 0) {
            throw new IllegalArgumentException("n debe ser impar y > 0");
        }
        a = a % n;
        if (a < 0) a += n;
        if (a == 0) return 0;
        int j = 1;

        while (a != 0) {
            // quitar factores de 2 de a
            while ((a & 1) == 0) { // a % 2 == 0
                a >>= 1; // a /= 2
                long nMod8 = n % 8;
                if (nMod8 == 3 || nMod8 == 5) {
                    j = -j;
                }
            }

            // intercambio a <-> n (reciprocidad cuadrática)
            long tmp = a;
            a = n;
            n = tmp;

            if ((a % 4 == 3) && (n % 4 == 3)) {
                j = -j;
            }

            a = a % n;
        }

        return (n == 1) ? j : 0;
    }

    public static boolean esPrimo(long n, int iteraciones) {
        if (n < 2) return false;
        if (n == 2) return true;
        if ((n & 1) == 0) return false; // par

        Random rand = new Random();

        for (int i = 0; i < iteraciones; i++) {
            // elegir a aleatorio en [2, n-2]
            long a = 2 + (Math.abs(rand.nextLong()) % (n - 3 + 1)); // seguro para n moderados

            // si gcd(a, n) > 1 entonces compuesto
            BigInteger bigA = BigInteger.valueOf(a);
            BigInteger bigN = BigInteger.valueOf(n);
            if (!bigA.gcd(bigN).equals(BigInteger.ONE)) {
                return false;
            }

            int jac = jacobi(a, n);
            if (jac == 0) return false;

            BigInteger modPow = bigA.modPow(BigInteger.valueOf((n - 1) / 2), bigN);

            // mapear jacobi: -1 -> n-1, 1 -> 1
            BigInteger esperado = (jac == -1) ? bigN.subtract(BigInteger.ONE) : BigInteger.valueOf(jac);

            if (!modPow.equals(esperado)) {
                return false; // compuesto
            }
        }
        return true; // probablemente primo
    }

    public static void main(String[] args) {
        System.out.println("137 primo? " + esPrimo(137, 10)); // true
        System.out.println("561 primo? " + esPrimo(561, 10)); // Carmichael number -> debería dar false en Solovay-Strassen
        System.out.println("7919 primo? " + esPrimo(7919, 10)); // true
    }
}

