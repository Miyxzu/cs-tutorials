package assessmenta;

import java.util.*;

public class SystemLog<T> {
    private List<T> logEntries;

    public SystemLog() {
        this.logEntries = new LinkedList<>();
    }

    public void add(T entry) {
        logEntries.add(entry);
    }

    public void printLog() {
        for (T entry : logEntries) {
            System.out.println(entry);
        }
    }
}
