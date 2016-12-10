package com.mySampleApplication.server;

/**
 * Created by HEAD on 09.11.2016.
 */
import com.google.gwt.core.client.GWT;


import java.io.BufferedReader;			//
import java.io.FileNotFoundException;	// For file reading
import java.io.FileReader;				//
import java.io.IOException;				//
import java.util.ArrayList;
//Call for imte

public class Data {
    //dt,AverageTemperature,AverageTemperatureUncertainty,City,Country,Latitude,Longitude
    private String csvFile = "data.csv";
    ArrayList<DataEntry> entryList = new ArrayList<DataEntry>();
    public Data() {
        loadData(csvFile);

    }

    public ArrayList<String[]> getData(String year){
        ArrayList<String[]> responseList = new ArrayList<String[]>();

        for (DataEntry curInstance: entryList) {
            try {
                if (curInstance.getDate().substring(0, 4).compareTo(year) == 0) {
                    responseList.add(curInstance.toStringArray());

                }
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(e.toString());
            }
        }
        return responseList;
    }


    public void loadData(String filename){

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] csvLine = line.split(cvsSplitBy);
                entryList.add(new DataEntry(csvLine));
                GWT.log(csvLine[1]);
            }
        }catch(IOException e){
            System.out.println("File not found" + e.toString());
        }

    }

    public ArrayList<String[]> filter(String query, int item){
        ArrayList<String[]> responseList = new ArrayList<String[]>();
       // query = query.toCharArray();

        for (DataEntry curInstance: entryList) {
            try {
                if(responseList.size()>1000){
                    String[] curr = new String[7];
                    for(int i=0;i<7;i++) {
                        curr[i] ="Too many entries<br> to display!";
                    }
                    responseList.add(curr);
                    break;
                }
                String[]cur = curInstance.toStringArray();
                CharSequence querySeq = query.toLowerCase();
                if (cur[item].toLowerCase().contains(querySeq)) {
                    responseList.add(cur);
                }

            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(e.toString());
            }
        }
        if(responseList.size()==0){
            String[] curr = new String[7];
            for(int i=0;i<7;i++) {
                curr[i] = "No entries";
            }
            responseList.add(curr);
        }
        return responseList;
    }
}
