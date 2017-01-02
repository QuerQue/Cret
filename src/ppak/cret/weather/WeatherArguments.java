package ppak.cret.weather;

/**
 * Argument holder class passed to WeatherFactory, which is later stored in WeatherType objects
 * @see WeatherFactory
 * @see ppak.cret.weather.types.WeatherType
 */
public class WeatherArguments {
    private double temperature;
    private double pressure;
    private double humidity;
    private double clouds;
    private String description;
    private String location;
    /**
     * WeatherArgument constructor sets all arguments.
     * @param temperature current temperature
     * @param pressure current pressure
     * @param humidity current humidity (percentage)
     * @param clouds current cloud coverage (percentage)
     * @param description overall description of weather conditions
     * @param location location - city name, comma, country
     */
    public WeatherArguments(
            double temperature, double pressure, double humidity,
            double clouds, String description, String location) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.clouds = clouds;
        this.description = description;
        this.location = location;

    }
    /**
     * Gets stored temperature value
     * @return temperature value
     */
    public double getTemperature() {
        return temperature;
    }
    /**
     * Gets stored pressure value
     * @return pressure value
     */
    public double getPressure() {
        return pressure;
    }
    /**
     * Gets stored humidity value
     * @return humidity value (percentage)
     */
    public double getHumidity() {
        return humidity;
    }
    /**
     * Gets stored cloud coverage value
     * @return cloud coverage value (percentage)
     */
    public double getClouds() {
        return clouds;
    }
    /**
     * Gets weather conditions description value
     * @return weather conditions description
     */
    public String getDescription() {
        return description;
    }
    /**
     * Gets location name
     * @return location name - city name, comma, country
     */
    public String getLocation() {
        return location;
    }
}
