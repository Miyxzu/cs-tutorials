package DSA.Queues;

import java.util.*;

public class queue {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();

        long time = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            if (isPrime(i)) {
                queue.add(i);
            }
        }
    
        System.out.println(queue);
        System.out.println(queue.size());
        System.out.println((System.currentTimeMillis() - time) + " ms");
    }

    static boolean isPrime(int n) { 
        // Corner case 
        if (n <= 1) 
            return false; 
  
        if (n == 2 || n == 3) 
            return true; 
  
        if (n % 2 == 0 || n % 3 == 0) 
            return false; 
  
        for (int i = 5; i < Math.sqrt(n); i = i + 6) 
            if (n % i == 0 || n % (i + 2) == 0) 
                return false; 
  
        return true; 
    } 
}

