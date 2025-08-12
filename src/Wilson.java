/*
 * Prueba de primalidad de Wilson
 * Algoritmo determinístico basado en el Teorema de Wilson:
 * (p-1)! ≡ -1 (mod p) si y solo si p es primo.
 * Exacto pero extremadamente lento para números grandes.
 */

public class Wilson {
    public static boolean esPrimo(int n) {
        if (n <= 1) return false;
        long fact = 1;
        for (int i = 2; i < n; i++) fact = (fact * i) % n;
        return (fact + 1) % n == 0;
    }

    public static void main(String[] args) {
        System.out.println(esPrimo(137));
    }
}
