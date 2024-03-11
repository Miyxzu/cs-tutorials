package dataStruc;

public class recursion {
    public static void main(String[] args) {
        String names[] = {"A", "B", "C", "D"};

        System.out.println("Recursion Methods:");
        System.out.println("M23: " + rec(5));
        System.out.print("N22: ");
        mysteryArr(names, 3);
        System.out.println();
        System.out.println("N20: " + mystery(7));
    }

    static int rec(int a){
        if (a >= 2) {
            return rec(a-1) + rec(a-2);
        } else {
            return 1;
        }
    }

    static void mysteryArr(String[] a, int n) {
        if (n > 0) {
            mysteryArr(a, n-1);
        }
        System.out.print(a[3-n] + " ");
    }
    
    static int mystery(int n) {
        if (n > 0) {
            return 3 + mystery(n-3);
        } else {
            return 3;
        }
    }
}
