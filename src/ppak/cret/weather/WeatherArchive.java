package ppak.cret.weather;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores data used in communication with database.
 * SQLConnector uses this class to retrieve data from database, which can be later shown on charts
 * @see ppak.cret.connector.SQLConnector
 */
public class WeatherArchive
{

    private List<Double> temperatureList = new ArrayList<Double>();
    private List<Double> pressureList = new ArrayList<Double>();
    private List<Double> humidityList = new ArrayList<Double>();
    private List<Double> cloudsList = new ArrayList<Double>();
    private List<String> descriptionList = new ArrayList<String>();
    private List<String> dateList = new ArrayList<String>();

    /**
     * Gets all temperature values stored in database for current City
     * @return list of temperature values
     */
    public List<Double> getTemperatureList() {
        return temperatureList;
    }
    /**
     * Sets all temperature values to be stored in database for current City
     * @param temperatureList list of temperature values to store
     */
    public void setTemperatureList(List<Double> temperatureList) {
        this.temperatureList = temperatureList;
    }
    /**
     * Gets all pressure values stored in database for current City
     * @return list of pressure values
     */
    public List<Double> getPressureList() {
        return pressureList;
    }
    /**
     * Sets all pressure values to be stored in database for current City
     * @param pressureList  list of pressures values to store
     */
    public void setPressureList(List<Double> pressureList) {
        this.pressureList = pressureList;
    }
    /**
     * Gets all humidity values stored in database for current City
     * @return list of humidity values
     */
    public List<Double> getHumidityList() {
        return humidityList;
    }
    /**
     * Sets all humidity values to be stored in database for current City
     * @param humidityList  list of humidity values to store
     */
    public void setHumidityList(List<Double> humidityList) {
        this.humidityList = humidityList;
    }
    /**
     * Gets all cloud coverage values stored in database for current City
     * @return list of cloud coverage values
     */
    public List<Double> getCloudsList() {
        return cloudsList;
    }
    /**
     * Sets all cloud coverage values to be stored in database for current City
     * @param cloudsList list of clouds coverage values to store
     */
    public void setCloudsList(List<Double> cloudsList) {
        this.cloudsList = cloudsList;
    }
    /**
     * Gets all weather descriptions stored in database for current City
     * @return list of cloud coverage values
     */
    public List<String> getDescriptionList() {
        return descriptionList;
    }
    /**
     * Sets all descriptions to be stored in database for current City
     * @param descriptionList list of description Strings to store
     */
    public void setDescriptionList(List<String> descriptionList) {
        this.descriptionList = descriptionList;
    }
    /**
     * Gets all dates stored in database for current City
     * @return list dates
     */
    public List<String> getDateList() {
        return dateList;
    }
    /**
     * Sets all date values to be stored in database for current City
     * @param dateList  list of date Strings to store
     */
    public void setDateList(List<String> dateList) {
        this.dateList = dateList;
    }

}
