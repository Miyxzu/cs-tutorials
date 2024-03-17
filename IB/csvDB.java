import com.opencsv.*;
import java.io.*;
import java.util.*;

public class csvDB {

    public static void main(String[] args) {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        System.out.println("CSV Data:");
        writeData();
        readDataOpenCSV();
    }

    /**
     * 
     */
    public static void writeData() {
        File file = new File("./IB/data.csv");
        try {
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);
            String[] header = { "Name", "Age", "City" };
            writer.writeNext(header);

            List<String[]> data = new ArrayList<String[]>();
            data.add(new String[] { "John", "25", "New York" });
            data.add(new String[] { "Tom", "30", "California" });
            data.add(new String[] { "Harry", "28", "Texas" });
            data.add(new String[] { "Sally", "35", "Washington" });
            data.add(new String[] { "Alice", "27", "Florida" });
            writer.writeAll(data);

            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readDataOpenCSV() {
        try {
            CSVReader csvReader = new CSVReader(new FileReader("./src/databases/data.csv"));
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                for (String cell : nextRecord) {
                    System.out.print(cell + "\t");
                }
                System.out.println();
            }
            csvReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readDataBufferedRead() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("./src/databases/data.csv"));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
