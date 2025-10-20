package assessmentA;

import java.util.*;

/**
 * A class to manage a database of plany species.
 * 
 * @author ppx
 * @version 1.0
 */
public class plantSpeciesDB {
    ArrayList<plantSpecies> plantSpeciesDB;

    /**
     * Constructor to initialize the ArrayList
     */
    public plantSpeciesDB() {
        plantSpeciesDB = new ArrayList<>();
    }

    /**
     * Adds a plant species to the database
     * 
     * @param name   Name of the plant species
     * @param region Region of origin
     * @param w      Width of the leaves
     * @param l      Length of the leaves
     * @return Returns true if the species was added, false if it already exists
     */
    public boolean addPlantSpecies(String name, String region, double w, double l) {
        boolean speciesExists = false;

        for (plantSpecies i : plantSpeciesDB) {
            if (i.getSpeciesName().equalsIgnoreCase(name)) {
                speciesExists = true;
                break;
            }
        }

        if (!speciesExists) {
            plantSpeciesDB.add(new plantSpecies(name, region, l, w));
            return true;
        } else {
            return false;
        }
    }

    /**
     * Removes a plant species from the database
     * 
     * @param name Name of the plant species to be removed
     * @return Returns true if the species was removed, false if it does not exist
     */
    public boolean removeSpecies(String name) {
        Iterator<plantSpecies> iterator = plantSpeciesDB.iterator();
        while (iterator.hasNext()) {
            plantSpecies species = iterator.next();
            if (species.getSpeciesName().equalsIgnoreCase(name)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Edits an existing species entry in the database
     * 
     * @param name Name of the species to be edited
     */
    public void editSpeciesEntry(String name) {
        int choice = 0;
        plantSpecies edited = null;
        Scanner in = new Scanner(System.in);
        for (plantSpecies i : plantSpeciesDB) {
            if (i.getSpeciesName().equalsIgnoreCase(name)) {
                // Edit logic here
                do {
                    System.out.print("Edit:\n1) Name\n2) Region\n3) Length\n4) Width\n5) Exit\n>> ");
                    choice = in.nextInt();
                    in.nextLine(); // Consume newline

                    switch (choice) {
                        case 1:
                            System.out.print("Enter new name:\n>> ");
                            String newName = in.nextLine();
                            i.setSpeciesName(newName);
                            break;
                        case 2:
                            System.out.print("Enter new region:\n>> ");
                            String newRegion = in.nextLine();
                            i.setRegion(newRegion);
                            break;
                        case 3:
                            System.out.print("Enter new length:\n>> ");
                            double newLength = in.nextDouble();
                            i.setLength(newLength);
                            break;
                        case 4:
                            System.out.print("Enter new width:\n>> ");
                            double newWidth = in.nextDouble();
                            i.setWidth(newWidth);
                            break;
                        default:
                            break;
                    }
                } while (choice != 5);
                edited = i;
                break;
            }
        }
        System.out.println("Updated Entry: \n" + edited);
    }

    /**
     * Calculates the evaporation based on the area of the leaf size of a species
     * 
     * @param species Gets the name of the species to be calculated
     * @return Returns the evaporation rate of the plant
     */
    public Double getEvaporationRate(String species) {
        double area = 0;

        for (plantSpecies i : plantSpeciesDB) {
            if (i.getSpeciesName().equalsIgnoreCase(species)) {
                area = (i.getLength() * i.getWidth());
            }
        }

        if (area < 60) {
            return 2.1;
        } else if (60 <= area && area <= 180) {
            return 4.3;
        } else if (180 <= area && area <= 3000) {
            return 7.7;
        } else if (3000 <= area && area <= 8000) {
            return 14.3;
        } else {
            return 24.5;
        }
    }

    /**
     * Displays the plant species database
     */
    public void displayDatabase() {
        System.out.printf("%-15s%-15s%-8s%-8s%n", "Species", "Region", "Length", "Width");
        for (plantSpecies i : plantSpeciesDB) {
            System.out.println(i);
        }
    }

    /**
     * Displays the plant species database sorted by user criteria
     * 
     * @param order  Ascending or Descending order
     * @param sortBy Criteria to sort by
     */
    public void displayByOrder(String order, String sortBy) {
        ArrayList<plantSpecies> sortedList = new ArrayList<>(plantSpeciesDB);

        switch (sortBy.toLowerCase()) {
            case "name":
                // Sort by Ascending order of Name
                if (order.equalsIgnoreCase("a")) {
                    Collections.sort(sortedList, (asc, desc) -> asc.getSpeciesName().compareTo(desc.getSpeciesName()));
                }

                // Sort by Descending order of Name
                else if (order.equalsIgnoreCase("d")) {
                    Collections.sort(sortedList, (asc, desc) -> desc.getSpeciesName().compareTo(asc.getSpeciesName()));
                }
                break;

            case "region":
                // Sort by Ascending order of Region
                if (order.equalsIgnoreCase("a")) {
                    Collections.sort(sortedList, (asc, desc) -> asc.getRegion().compareTo(desc.getRegion()));
                }

                // Sort by Descending order of Region
                else if (order.equalsIgnoreCase("d")) {
                    Collections.sort(sortedList, (asc, desc) -> desc.getRegion().compareTo(asc.getRegion()));
                }
                break;

            case "length":
                // Sort by Ascending order of Length
                if (order.equalsIgnoreCase("a")) {
                    Collections.sort(sortedList, (asc, desc) -> asc.getLength().compareTo(desc.getLength()));
                }

                // Sort by Descending order of Length
                else if (order.equalsIgnoreCase("d")) {
                    Collections.sort(sortedList, (asc, desc) -> desc.getLength().compareTo(asc.getLength()));
                }
                break;

            case "width":
                // Sort by Ascending order of Width
                if (order.equalsIgnoreCase("a")) {
                    Collections.sort(sortedList, (asc, desc) -> asc.getWidth().compareTo(desc.getWidth()));
                }

                // Sort by Descending order of Width
                else if (order.equalsIgnoreCase("d")) {
                    Collections.sort(sortedList, (asc, desc) -> desc.getWidth().compareTo(asc.getWidth()));
                }
                break;
            default:
                break;
        }

        System.out.printf("%-15s%-15s%-8s%-8s%n", "Species", "Region", "Length", "Width");
        for (plantSpecies i : sortedList) {
            System.out.println(i);
        }
    }

    /**
     * Searches through the database using name and/or region
     * 
     * @param name   Name of the species to be searched
     * @param region Region of origin to be searched
     */
    public void searchDatabase(String name, String region) {
        System.out.printf("%-15s%-15s%-8s%-8s%n", "Species", "Region", "Length", "Width");

        // If both parameters were passed
        if (name != null && region != null) {
            for (plantSpecies i : plantSpeciesDB) {
                if (i.getSpeciesName().equalsIgnoreCase(name) && i.getRegion().equalsIgnoreCase(region)) {
                    System.out.println(i);
                }
            }
        }
        // If only name was passed
        else if (name != null) {
            for (plantSpecies i : plantSpeciesDB) {
                if (i.getSpeciesName().equalsIgnoreCase(name)) {
                    System.out.println(i);
                }
            }
        }
        // If only region was passed
        else if (region != null) {
            for (plantSpecies i : plantSpeciesDB) {
                if (i.getRegion().equalsIgnoreCase(region)) {
                    System.out.println(i);
                }
            }
        }
    }

    /**
     * Searches through the database using wildcards
     * 
     * @param name Section of names to be searched
     */
    public void wildcardSearch(String name) {
        System.out.printf("%-15s%-15s%-8s%-8s%n", "Species", "Region", "Length", "Width");
        for (plantSpecies i : plantSpeciesDB) {
            if (i.getSpeciesName().startsWith(name)) {
                System.out.println(i);
            }
            if (i.getSpeciesName().endsWith(name)) {
                System.out.println(i);
            }
        }
    }
}

/**
 * A class to represent a plant species with its attributes.
 * 
 * @author xdd
 * @version 1.0
 */
class plantSpecies {
    private String speciesName;
    private String region;
    private Double length;
    private Double width;

    /**
     * Default constructor with default values
     */
    public plantSpecies() {
        speciesName = "N/A";
        region = "Unknown";
        length = 0.0;
        width = 0.0;
    }

    /**
     * Parameterized constructor
     * 
     * @param speciesName Scientific name of the plant species
     * @param region      Region of origin
     * @param length      Length of the leaves of the plant species
     * @param width       Width of the leaves of the plant species
     */
    public plantSpecies(String speciesName, String region, Double length, Double width) {
        this.speciesName = speciesName;
        this.region = region;
        this.length = length;
        this.width = width;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    /**
     * String representation of the plant species
     * 
     * @return formatted string of plant species attributes
     */
    @Override
    public String toString() {
        return String.format("%-15s%-15s%-8.2f%-8.2f", speciesName, region, length, width);
    }
}
