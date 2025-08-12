import java.math.BigInteger;
import java.util.Random;

/*
 * Prueba de primalidad de Fermat
 * Algoritmo probabilístico basado en el Pequeño Teorema de Fermat:
 * si p es primo, entonces a^(p-1) ≡ 1 (mod p) para a no múltiplo de p.
 * Fácil de implementar pero engañado por números de Carmichael.
 */

public class Fermat {
    public static boolean esPrimo(long n, int iteraciones) {
        if (n <= 1 || n == 4) return false;
        if (n <= 3) return true;

        Random rand = new Random();
        for (int i = 0; i < iteraciones; i++) {
            long a = 2 + rand.nextInt((int) (n - 4));
            if (BigInteger.valueOf(a).modPow(BigInteger.valueOf(n - 1), BigInteger.valueOf(n)).intValue() != 1)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(esPrimo(137, 5));
    }
}
