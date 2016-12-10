import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



public class Data {
    //dt,AverageTemperature,AverageTemperatureUncertainty,City,Country,Latitude,Longitude
    private String csvFile = "/Users/Till/Desktop/Software_Engineering_Eclipse/Testing/src/dummyFile.csv";
    public static ArrayList<DataEntry> entryList = new ArrayList<DataEntry>();
    public Data() {
        loadData(csvFile);
    }

    public ArrayList<String[]> getData(String year){
        ArrayList<String[]> responseList = new ArrayList<String[]>();

        for (DataEntry curInstance: entryList) {
            if(curInstance.getDate().compareTo(year) == 0){
                responseList.add(curInstance.toStringArray());
            }
        }
        return responseList;
    }

    public static void loadData(String filename){

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] csvLine = line.split(cvsSplitBy);
                entryList.add(new DataEntry(csvLine));
                

            }
        }catch(IOException e){
            System.out.println("File not found" + e.toString());
        }

    }
}
