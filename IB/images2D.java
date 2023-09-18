public class images2D {
    public static void main(String[] args) {

        int[][] image = {{0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                         {0, 0, 0, 1, 0, 0, 0, 0, 1, 0},
                         {0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
                         {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                         {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                         {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                         {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                         {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                         {0, 0, 0, 1, 1, 1, 1, 1, 0, 0},
                         {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

        System.out.println(invert(10, image));
        System.out.println();
        System.out.println(invertDaniel(10, image));
    }

    static int[][] invert(int n, int[][] a){
        int[][] inverted = new int[n][n];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if(a[i][j] == 1){
                    inverted[i][j] = 0;
                }
                if(a[i][j] == 0){
                    inverted[i][j] = 1;
                }
            }
        }

        for (int i = 0; i < inverted.length; i++) {
            for (int j = 0; j < inverted[i].length; j++) {
                System.out.print(inverted[i][j] + " ");
            }
            System.out.println();
        }

        return inverted;
    }

    static int[][] invertDaniel(int n, int[][] a){
        int[][] inverted = new int[n][n];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                inverted[i][j] = 1 - a[i][j];
            }
        }

        for (int i = 0; i < inverted.length; i++) {
            for (int j = 0; j < inverted[i].length; j++) {
                System.out.print(inverted[i][j] + " ");
            }
            System.out.println();
        }

        return inverted;
    }

    static int[][] rotate(int n, int[][] a){
        int[][] b = new int[n][n];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                
            }
        }
        
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                System.out.print(b[i][j] + " ");
            }
            System.out.println();
        }

        return b;
    }
}
