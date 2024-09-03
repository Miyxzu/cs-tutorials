package otherStuff;

public class perfectNumber {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        for (int p = 2; p < 36; p++) { // Limit based on known perfect numbers
            long mersenneNumber = (1L << p) - 1; // Equivalent to 2^p - 1
            if (isPrime(mersenneNumber)) {
                long perfectNumber = mersenneNumber * (1L << (p - 1)); // Equivalent to (2^p - 1) * 2^(p-1)
                System.out.println(perfectNumber);
            }
        }

        System.out.println("Time: " + (System.currentTimeMillis() - startTime) / 1000.0 + " seconds");
    }

    static boolean isPrime(long n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (long i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
