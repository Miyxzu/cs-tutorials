import java.util.*;
import java.io.*;

public class test {
    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        String temp = "";

        while (!checkISBN(temp)) {
            temp = genISBN();
            System.out.println(temp);
        }


        Long end = System.currentTimeMillis();
        System.out.println("Final ISBN: " + temp);
        System.out.println("Time taken: " + (end - start) + "ms");
    }

    static Boolean checkISBN(String isbn) {
        if (isbn.length() != 13) {
            return false;
        }

        int sum = 0;
        int lastDigit = 0;
        for (int i = 0; i < isbn.length() - 1; i++) {
            int digit = Character.getNumericValue(isbn.charAt(i));
            if (i == isbn.length() - 1) {
                lastDigit = digit;
            }
            if (i % 2 == 0) {
                sum += (digit * 3);
            } else {
                sum += digit;
            }
        }

        int checkSum = (10 - (sum % 10)) % 10;

        if (lastDigit == checkSum) {
            return true;
        } else {
            return false;
        }
    }

    static String genISBN() {
        Random rand = new Random();
        
        int firstDigit = rand.nextInt(10);
        StringBuilder isbnBuilder = new StringBuilder(String.valueOf(firstDigit));
        
        for (int i = 0; i < 12; i++) {
            int digit = rand.nextInt(10);
            isbnBuilder.append(digit);
        }
        
        String isbn = isbnBuilder.toString();
        return isbn;
    }
}
