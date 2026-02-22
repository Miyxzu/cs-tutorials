package assessmenta;

import java.util.*;

public class IncidentResponse {

    private Deque<Incident> incidentQueue;
    // private Map<String, Deque<Incident>> districtIncidents;
    private Set<String> incidentTypes;
    private Set<String> yesterdaysIncidents = Set.of("Medical", "Security", "Traffic");

    public IncidentResponse() {
        this.incidentQueue = new LinkedList<>();
        this.incidentTypes = new HashSet<>();
    }

    public void addIncident(String type, String district, boolean priority) {
        if (priority) {
            incidentQueue.addFirst(new Incident(type, district, priority));
        } else {
            incidentQueue.addLast(new Incident(type, district, priority));
        }
        incidentTypes.add(type);
    }

    public void currentIncidents() {
        System.out.println("Current incidents in queue:");
        for (Incident i : incidentQueue) {
            System.out.printf("%-20s%-20s%-15s%-15s%-10s%-10s%n", "ID", "Timestamp", "Incident Type", "District",
                    "Duration", "Priority");
            System.out.println(i);
        }
    }

    public void assignIncident() {
        if (incidentQueue.isEmpty()) {
            System.out.println("No incidents to assign");
        } else {
            System.out.println(incidentQueue.pollFirst());
        }
    }

    public void incidentOccurred() {
        if (!incidentTypes.isEmpty()) {
            System.out.println("Unique incident types recorded:");
            for (String type : incidentTypes) {
                System.out.println("- " + type);
            }
        } else {
            System.out.println("No incidents recorded yet");
        }
    }

    public void searchIncidentDatabase(String type, String district) {
        boolean check = false;
        System.out.printf("%-20s%-20s%-15s%-15s%-10s%-10s%n", "ID", "Timestamp", "Incident Type", "District",
                "Duration", "Priority");
                
        if (type != null && district != null) {
            for (Incident i : incidentQueue) {
                if (i.getType().equalsIgnoreCase(type) && i.getDistrict().equalsIgnoreCase(district)) {
                    System.out.println(i);
                }
            }
            check = true;

        }
        if (type != null) {
            for (Incident i : incidentQueue) {
                if (i.getType().equalsIgnoreCase(type)) {
                    System.out.println(i);
                }
            }
            check = true;

        }
        if (district != null) {
            for (Incident i : incidentQueue) {
                if (i.getDistrict().equalsIgnoreCase(district)) {
                    System.out.println(i);
                }
            }
            check = true;
        }

        if (!check) {
            System.out.println("No incidents found");
        }
    }

    public void trendAnalysis() {
        System.out.println("Incidents from today: " + incidentTypes);
        System.out.println("Incidents from yesterday: " + yesterdaysIncidents);

        // Union of sets
        Set<String> union = new HashSet<>(incidentTypes);
        union.addAll(yesterdaysIncidents);
        System.out.println("\nUnion of incident types: " + union);

        // Intersection of sets
        Set<String> intersection = new HashSet<>(incidentTypes);
        intersection.retainAll(yesterdaysIncidents);
        System.out.println("Intersection of incident types: " + intersection);

        // Difference of sets
        Set<String> difference = new HashSet<>(incidentTypes);
        difference.removeAll(yesterdaysIncidents);
        System.out.println("Difference of incident types: " + difference);
    }

    public Incident getHeadIncident() {
        if (incidentQueue.isEmpty()) {
            return null;
        }
        return incidentQueue.getFirst();
    }
}