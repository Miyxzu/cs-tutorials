import java.util.Random;

public class varScope {
    public static void main(String[] args) {
        new DiceRoller();
    }
}

class DiceRoller {
    int number;
    Random rand;

    DiceRoller(){
        rand = new Random();
        roll();
    }

    void roll(){
        number = rand.nextInt(6) + 1;
        System.out.println(number);
    }
}
