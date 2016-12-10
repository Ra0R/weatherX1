
public class TestDriver {
	public static String[] sarray;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Data data = new Data();
		
		
		
		for(DataEntry i: Data.entryList){
			 sarray = i.toStringArray();
			
	}
		String[] expected = {"dt","AverageTemperature","AverageTemperatureUncertainty","City","Country","Latitude","Longitude","1849-01-01","26.704","1.435","Abidjan","CÃ´te D'Ivoire","5.63N","3.23W"};
	
		System.out.println(expected.equals(sarray));
}

}
