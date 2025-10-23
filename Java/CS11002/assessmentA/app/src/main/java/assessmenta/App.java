package assessmentA;

import java.util.*;


/**
 * Main application class for the Plant Species Database
 */
public class App {
    static Scanner in;

    /**
     * Main method to run the Plant Species Database application
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        in = new Scanner(System.in);
        plantSpeciesDB db = new plantSpeciesDB();
        int choice = 0;
        String[] valid = { "name", "region", "length", "width", "a", "d" };
        String search, name = null, region = null;
        double length = 0.0, width = 0.0;

        do {
            try {
                System.out.print("Welcome to the Plant Species Database\n" +
                        "1) Add Plant Species\n" +
                        "2) Edit Species Entry\n" +
                        "3) Remove Plant Species\n" +
                        "4) Display Species Database\n" +
                        "5) Search Database\n" +
                        "6) Advanced/Wildcard Search\n" +
                        "7) Calculate Evaporation Rate\n" +
                        "8) Exit\n" +
                        ">> ");

                choice = in.nextInt();

                switch (choice) {
                    // Add Plant Species
                    case 1:
                        in.nextLine(); // Consume leftover newline
                        System.out.print("\nSpecies Name\n>> ");
                        name = in.nextLine();
                        System.out.print("Region of Origin\n>> ");
                        region = in.nextLine();

                        // Read and validate Length
                        while (length <= 1.3 || length >= 25000) {
                            System.out.print("Length (mm)\n>> ");
                            length = in.nextDouble();
                            if (length <= 1.3) {
                                System.out.println("Length must be greater than 1.3");
                            } else if (length >= 25000) {
                                System.out.println("Length must be less than 25000");
                            }
                        }

                        // Read and validate Width
                        while (length <= 1.3 || length >= 25000) {
                            System.out.print("Width (mm)\n>> ");
                            width = in.nextDouble();
                            if (width <= 1) {
                                System.out.println("Width must be greater than 1");
                            } else if (width >= 3000) {
                                System.out.println("Width must be less than 3000");
                            }
                        }

                        // Check if species does not exist and add
                        if (db.addPlantSpecies(name, region, width, length)) {
                            System.out.println("Species entry added successfully");
                        } else {
                            System.out.println("Species already exists");
                        }
                        clearScreen();
                        break;

                    // Edit Species Entry
                    case 2:
                        in.nextLine(); // Consume leftover newline
                        db.displayDatabase();

                        System.out.print("\nEnter the name of the species you want to edit:\n>> ");
                        name = in.nextLine();
                        db.editSpeciesEntry(name, in);
                        clearScreen();
                        break;

                    // Remove Plant Species
                    case 3:
                        in.nextLine(); // Consume leftover newline
                        db.displayDatabase();

                        System.out.print("\nEnter the name of the species you want to remove:\n>> ");
                        name = in.nextLine();

                        // Check if species exists and remove
                        if (db.removeSpecies(name)) {
                            System.out.println("Species entry removed successfully");
                        } else {
                            System.out.println("Species does not exist");
                        }
                        clearScreen();
                        break;

                    // Display Species Database
                    case 4:
                        in.nextLine(); // Consume leftover newline
                        String sort;

                        do {
                            System.out.print("Sort by: Name, Region, Length, Width\n>> ");
                            search = in.nextLine();
                        } while (!Arrays.asList(valid).contains(search.toLowerCase()));

                        do {
                            System.out.print("Asc (a) or Desc (d)\n>> ");
                            sort = in.nextLine();
                        } while (!Arrays.asList(valid).contains(sort.toLowerCase()));

                        db.displayByOrder(sort, search);
                        clearScreen();
                        break;

                    // Search Database
                    case 5:
                        in.nextLine(); // Consume leftover newline
                        int select;
                        name = null;
                        region = null;

                        do {
                            System.out.print("Search by:\n1) Name\n2) Region\n3) Name/Region\n>>");
                            select = in.nextInt();

                            switch (select) {
                                case 1:
                                    System.out.println("Name of Species >> ");
                                    name = in.nextLine();
                                    select = 0;

                                    if (name == null || name.trim().isEmpty()) {
                                        System.out.println("Name cannot be empty");
                                        break;
                                    }

                                    break;
                                case 2:
                                    System.out.println("Region of Origin >> ");
                                    region = in.nextLine();
                                    select = 0;

                                    if (region == null || region.trim().isEmpty()) {
                                        System.out.println("Region cannot be empty");
                                        break;
                                    }

                                    break;
                                case 3:
                                    System.out.println("Name of Species >> ");
                                    name = in.nextLine();
                                    System.out.println("Region of Origin >> ");
                                    region = in.nextLine();

                                    if (name == null || name.trim().isEmpty()) {
                                        System.out.println("Name cannot be empty");
                                        break;
                                    }
                                    if (region == null || region.trim().isEmpty()) {
                                        System.out.println("Region cannot be empty");
                                        break;
                                    }

                                    select = 0;
                                    break;
                                default:
                                    System.out.println("Invalid option");
                                    break;
                            }
                        } while (select != 0);

                        db.searchDatabase(name, region);
                        clearScreen();
                        break;

                    // Advanced/Wildcard Search
                    case 6:
                        in.nextLine(); // Consume leftover newline
                        String check = "";

                        System.out.print("\nEnter starting or ending name of Species (i.e. Heli* / *ing):\n>> ");
                        search = in.nextLine();
                        for (int i = 0; i < search.length(); i++) {
                            if (search.substring(i).contains("*")) {
                                if (search.indexOf("*") == 0) {
                                    check = search.substring(1, search.length());
                                    break;
                                }
                                check = search.substring(0, search.indexOf("*"));
                                break;
                            }
                        }

                        db.wildcardSearch(check);
                        clearScreen();
                        break;

                    // Calculate Evaporation Rate
                    case 7:
                        db.displayDatabase();
                        in.nextLine(); // Consume leftover newline

                        System.out.print("\nEnter Species Name:\n>> ");
                        name = in.nextLine();
                        System.out.println(name + "'s Evaporation Rate is: " + db.getEvaporationRate(name) + " mm/day");
                        clearScreen();
                        break;

                    // Exit
                    case 8:
                        choice = -1;
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input");
                in.next();
                clearScreen();
            } catch (NoSuchElementException e) {
                System.out.println("Error reading input");
                clearScreen();
            } catch (Exception e) {
                System.out.println("Now how in the hell did you get this?: " + e.getMessage());
                clearScreen();
            }
        } while (choice != -1);
        System.out.println("Thank you for using the Plant Species Database!");
        in.close();
    }

    /**
     * Clears the console
     */
    static void clearScreen() {
        if (in.hasNextLine()) {
            @SuppressWarnings("unused")
            String leftover = in.nextLine();
        }
        System.out.println("Press Enter to continue...");
        in.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}