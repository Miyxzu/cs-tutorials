import java.util.ArrayList;

public class listarrays {
    public static void main(String[] args) {
        ArrayList<String> food = new ArrayList<String>();

        food.add("fish");
        food.add("chicken");
        food.add("beef");

        food.set(0, "lamb");
        System.out.println(food);
        System.out.println();

        for(int i = 0; i < food.size(); i++){
            System.out.println(food.get(i));
        }
        System.out.println();

        food.remove(1);
        System.out.println(food);
        System.out.println();
        
        food.clear();
        System.out.println(food);
    }
}
