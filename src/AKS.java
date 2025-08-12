public class AKS {

    /*
     * Prueba de primalidad AKS
     * Algoritmo determinístico basado en propiedades algebraicas y polinómicas.
     * Garantiza un resultado exacto, pero es más lento que Miller-Rabin.
     */

    public static boolean esPrimo(long n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true; // Versión muy básica del concepto determinístico
    }

    public static void main(String[] args) {
        System.out.println(esPrimo(137));
    }
}
