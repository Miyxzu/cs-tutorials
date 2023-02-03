public class Food {
    String name;

    Food(String name){
        this.name = name;
    }
}

class mainObjOfArray{
    public static void main(String[] args) {
    Food[] refrigerator = new Food[3];

    Food f1 = new Food("Ramen");
    Food f2 = new Food("Udon");
    Food f3 = new Food("Soba");

    refrigerator[0] = f1;
    refrigerator[1] = f2;
    refrigerator[2] = f3;

    System.out.println(refrigerator[0].name);
    System.out.println(refrigerator[1].name);
    System.out.println(refrigerator[2].name);
    }
}
