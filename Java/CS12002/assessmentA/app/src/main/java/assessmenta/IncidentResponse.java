package assessmenta;

import java.util.*;

public class IncidentResponse {
    private Deque<Incident> incidentQueue;
    // private Map<String, Deque<Incident>> districtIncidents;
    private Set<String> incidentTypes;
    private Set<String> yesterdaysIncidents = Set.of("");

    public IncidentResponse() {
        this.incidentQueue = new LinkedList<>();
        this.incidentTypes = new HashSet<>();
    }

    public void addIncident(String type, String district, boolean priority) {
        if (priority) {
            incidentQueue.addFirst(new Incident(type, district));
        } else {
            incidentQueue.addLast(new Incident(type, district));
        }
        incidentTypes.add(type);
    }

    public void assignIncident() {
        if (incidentQueue.isEmpty()) {
            System.out.println("No incidents to assign");
        } else {
            System.out.printf("%-25s%-15s%-15s%-8d%n", "ID", "Incident Type", "District", "Timestamp");
            System.out.println(incidentQueue.poll());
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
        System.out.printf("", "ID", "Incident Type", "District", "Timestamp");

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
        // Union of sets
        Set<String> union = new HashSet<>(incidentTypes);
        union.addAll(yesterdaysIncidents);
        System.out.println("Union of incident types: " + union);

        // Intersection of sets
        Set<String> intersection = new HashSet<>(incidentTypes);
        intersection.retainAll(yesterdaysIncidents);
        System.out.println("Intersection of incident types: " + intersection);

        // Difference of sets
        Set<String> difference = new HashSet<>(incidentTypes);
        difference.removeAll(yesterdaysIncidents);
        System.out.println("Difference of incident types: " + difference);
    }
}

// leave for last once everything else is done
class SystemLog<T> {
    private List<T> logEntries;
}