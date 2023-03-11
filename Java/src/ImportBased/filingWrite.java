package ImportBased;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class filingWrite {
    public static void main(String[] args) {
        try {
            FileWriter filewrite = new FileWriter("/Users/66000/cs-tutorials/Java/src/ImportBased/kickback.txt");
            File file = new File("/Users/66000/cs-tutorials/Java/src/ImportBased/kickback.txt");
            if (file.exists()) {
                System.out.println("File does exist ");
                filewrite.write("ハッピーで埋め尽くして\nレストインピースまで行こうぜ");
                filewrite.append("\n-Kenshi Yonezu");
                System.out.println();
                System.out.println("Notice: Wrote to file " + file.getName());
            } else {
                System.out.println("File does not exist");
            }
            filewrite.close();
        } catch (IOException IOEx) {
            IOEx.printStackTrace();
        }
    }
}
