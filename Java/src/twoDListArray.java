import java.util.ArrayList;

public class twoDListArray {
    public static void main(String[] args) {
        ArrayList<ArrayList<String>> names = new ArrayList<>();
        
        ArrayList<String> gentlemen = new ArrayList<>();
        gentlemen.add("Jean");
        gentlemen.add("Connor");
        gentlemen.add("Taerim");

        ArrayList<String> ogs = new ArrayList<>();
        ogs.add("Noah");
        ogs.add("Kiran");

        ArrayList<String> realOnes = new ArrayList<>();
        realOnes.add("Sutrisno");
        realOnes.add("Alan");
        realOnes.add("Will");

        ArrayList<String> other = new ArrayList<>();
        other.add("Rishahb");
        other.add("Jeong Min");
        other.add("Darron");
        other.add("Jungwoo");

        names.add(gentlemen);
        names.add(ogs);
        names.add(realOnes);
        names.add(other);

        System.out.println(names);
    }
}
