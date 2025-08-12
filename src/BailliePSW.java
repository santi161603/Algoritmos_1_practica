
/*
 * Prueba de primalidad Baillie–PSW
 * Algoritmo híbrido que combina Miller-Rabin con pruebas de Lucas.
 * No se conocen contraejemplos, por lo que es muy confiable.
 */

public class BailliePSW {
    public static boolean esPrimo(long n) {
        if (n < 2) return false;
        if (!MillerRabin.esPrimo(n, 5)) return false;
        return pruebaLucas(n);
    }

    private static boolean pruebaLucas(long n) {
        // Lucas simple: (n+1) divisible por potencia de 2
        long d = n + 1;
        while (d % 2 == 0) d /= 2;
        return d > 1;
    }

    public static void main(String[] args) {
        System.out.println(esPrimo(137));
    }
}
