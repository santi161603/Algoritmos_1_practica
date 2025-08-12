import java.math.BigInteger;
import java.util.Random;

/*
 * Prueba de primalidad de Solovay-Strassen
 * Algoritmo probabilístico que mejora a Fermat usando el símbolo de Jacobi
 * y residuos cuadráticos para reducir falsos positivos.
 */

public class SolovayStrassen {
    public static boolean esPrimo(long n, int iteraciones) {
        if (n < 2) return false;
        if (n != 2 && n % 2 == 0) return false;

        Random rand = new Random();
        for (int i = 0; i < iteraciones; i++) {
            long a = 2 + rand.nextInt((int) (n - 2));
            long jacobi = BigInteger.valueOf(a).mod(BigInteger.valueOf(n)).jacobi(BigInteger.valueOf(n)).intValue();
            if (jacobi == 0) return false;
            BigInteger modPow = BigInteger.valueOf(a)
                    .modPow(BigInteger.valueOf((n - 1) / 2), BigInteger.valueOf(n));
            if (modPow.intValue() != (jacobi + n) % n) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(esPrimo(137, 5));
    }
}
