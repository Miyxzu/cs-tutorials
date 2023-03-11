package ArrayBased;

public class twoDArrays {
    public static void main(String[] args) {
        String[][] fruit = new String[3][3];

        fruit[0][0] = "Apple";
        fruit[0][1] = "Banana";
        fruit[0][2] = "Orange";
        fruit[1][0] = "Blueberry";
        fruit[1][1] = "Raspberry";
        fruit[1][2] = "Blackberry";
        fruit[2][0] = "Melon";
        fruit[2][1] = "Starfruit";
        fruit[2][2] = "Dragonfruit";

        for(int i = 0; i < fruit.length; i++){
            System.out.println();
            for(int l = 0; l < fruit[i].length; l++){
                System.out.print(fruit[i][l] + " ");
            }
        }

        System.out.println();

        
    }
}
