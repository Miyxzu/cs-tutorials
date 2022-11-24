import java.util.ArrayList;

public class listarrays {
    public static void main(String[] args) {
        ArrayList<String> food = new ArrayList<String>();

        food.add("fish");
        food.add("chicken");
        food.add("beef");

        food.set(0, "lamb");
        food.remove(1);
        food.clear();

        for(int i = 0; i < food.size(); i++){
            System.out.println(food.get(i));
        }
    }
}
