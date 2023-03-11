package ArrayBased;

import java.util.ArrayList;

public class listArrays {
    public static void main(String[] args) {
        ArrayList<String> food = new ArrayList<String>();

        food.add("fish");
        food.add("chicken");
        food.add("beef");
        food.add("fish");

        food.set(0, "lamb");
        System.out.println(food);
        System.out.println();

        for(String i : food){
        System.out.println(i);
        }
        System.out.println();

        System.out.println(food.get((food.size() - 1) / 2));

        food.remove(1);
        System.out.println(food);
        System.out.println();
        
        food.add(null);

        System.out.println(food);

        food.clear();
    }
}
