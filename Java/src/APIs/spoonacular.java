package APIs;

import java.net.*;
import java.io.*;
import java.util.*;
import org.json.*;

public class spoonacular {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String key = "35e52844f4a943399e87ae7901d75ec4";
        
        System.out.print("Enter an ingredient > ");
        String ingredient = in.nextLine();
        System.out.print("How many max grams of fat per serving? > ");
        int maxFat = in.nextInt();
        String test = "https://api.spoonacular.com/recipes/complexSearch?apiKey=" + key + "&query=" + ingredient
                + "&maxFat=" + maxFat + "&number=20";
        try {
            URI uri = new URI(test);
            HttpURLConnection conn = (HttpURLConnection) uri.toURL().openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed: HTTP error code: " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder response = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                response.append(output);
            }
            
            JSONObject jsonObject = new JSONObject(response.toString());
            JSONArray recipes = jsonObject.getJSONArray("results");
            
            for (int i = 0; i < recipes.length(); i++) {
                JSONObject recipe = recipes.getJSONObject(i);
                int id = recipe.getInt("id");
                String title = recipe.getString("title");
                String image = recipe.getString("image");
                System.out.println("ID: " + id + ", Title: " + title + ", Image: " + image);
                System.out.println();
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
