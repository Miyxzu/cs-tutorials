package assessmentA;

import java.util.*;

public class plantSpeciesDB {
    ArrayList<plantSpecies> plantSpeciesDB;
    
    public void addPlantSpecies() {
        
    }

    public void displayByOrder(String order, String sortBy) {
        switch (sortBy.toLowerCase()) {
            case "name":
                System.out.printf("%-15s %-15s %-8s %-8s","Species","Region","Length","Width");
                
                break;
            case "region":

                break;
            case "length":

                break;
            case "width":

                break;
            default:
                break;
        }
    }
}

class plantSpecies {
    private String speciesName;
    private String region;
    private Double length;
    private Double width;

    public plantSpecies() {
        speciesName = "N/A";
        region = "Unknown";
        length = 0.0;
        width = 0.0;
    }

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
}
