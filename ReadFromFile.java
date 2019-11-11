import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFromFile {

    /** Reads data from file */
    public static String[][] read(String file){

        File myCSVfile = new File(file);
        String line = "";
        String CSVsplitter = ","; // comma as seperator
        int i = 0;
        String[][] tab;

        try (BufferedReader br = new BufferedReader(new FileReader(myCSVfile))) {

            while(br.readLine() != null) {
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        tab = new String[i][i];

        try (BufferedReader br = new BufferedReader(new FileReader(myCSVfile))) {

            int j = 0;
            while ((line = br.readLine()) != null && j < tab.length) {
                String[] lineTokens = line.split("\n");
                tab[j] = lineTokens[0].split(CSVsplitter);
                j++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return tab;
    }
}
