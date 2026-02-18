package assessmenta;

import java.time.*;
import java.util.*;

public class Incident {

    private String type;
    private String district;
    private UUID id;
    private Instant timestamp;

    public Incident(String type, String district) {
        this.type = type;
        this.district = district;
        this.id = UUID.randomUUID();
        this.timestamp = Instant.now();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return String.format("%-40s%-15s%-15s%-8d%n", id, type, district, (Duration.between(timestamp, Instant.now()).toSeconds()));
    }
}
