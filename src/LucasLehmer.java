/*
 * Prueba de primalidad de Lucas–Lehmer
 * Algoritmo determinístico usado exclusivamente para números de Mersenne (2^p - 1).
 * Usa una secuencia especial para verificar si es primo.
 */

public class LucasLehmer {
    public static boolean esPrimoMersenne(int p) {
        if (p == 2) return true;
        long m = (1L << p) - 1;
        long s = 4;
        for (int i = 0; i < p - 2; i++) {
            s = (s * s - 2) % m;
        }
        return s == 0;
    }

    public static void main(String[] args) {
        System.out.println(esPrimoMersenne(7)); // 2^7 - 1 = 127
    }
}
