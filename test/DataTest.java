import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class DataTest {
	private String csvFile = "/Users/Till/Desktop/Software_Engineering_Eclipse/Testing/src/dummyFile.csv";

	@Test
	public void testloadData() {
		ArrayList<String[]> expected = new ArrayList<>();
		
		//expected.add(new String[]{"dt","AverageTemperature","AverageTemperatureUncertainty","City","Country","Latitude","Longitude"});
		expected.add(new String[]{"1849-01-01","26.704","1.435","Abidjan","CÃ´te D'Ivoire","5.63N","3.23W"});
		

		Data.loadData(csvFile);
		String[] sarray = null;
		for(DataEntry i: Data.entryList){
			 String[] sarray1 = i.toStringArray();
			 sarray = sarray1;
			}
		
		for(String[] expectedEntry : expected){
			Assert.assertArrayEquals("Line read correctly", expectedEntry, sarray);
		}
	}

}
