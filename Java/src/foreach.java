import java.util.ArrayList;

public class foreach {
    public static void main(String[] args) {
        String[] westCurrency = {"GBP", "EUR", "CAD", "USD"};
        ArrayList<String> eastCurrency = new ArrayList<String>();
        eastCurrency.add("JPY");
        eastCurrency.add("KRW");
        eastCurrency.add("SGD");
        eastCurrency.add("CNY");

        for(String i : westCurrency){
            System.out.println(i);
        }

        System.out.println();

        for(String i : eastCurrency){
            System.out.println(i);
        }
    }
}
