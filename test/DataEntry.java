
public class DataEntry {
    private String Date;
    private String AverageTemperature;
    private String AverageTemperatureUncertainty;
    private String City;
    private String Country;
    private String Latitude;
    private String Longitude;

    public DataEntry(String[] csvLine){
        this.Date = csvLine[0];
        this.AverageTemperature = csvLine[1];
        this.AverageTemperatureUncertainty =  csvLine[2];
        this.City = csvLine[3];
        this.Country = csvLine[4];
        this.Latitude = csvLine[5];
        this.Longitude = csvLine[6];
    }

    public String getDate(){
        return this.Date;
    }

    public String[] toStringArray(){
        String[] output = new String[7];
        output[0] = this.Date;
        output[1] = this.AverageTemperature;
        output[2] = this.AverageTemperatureUncertainty;
        output[3] = this.City;
        output[4] = this.Country;
        output[5] = this.Latitude;
        output[6] = this.Longitude;
        return output;
    }
}