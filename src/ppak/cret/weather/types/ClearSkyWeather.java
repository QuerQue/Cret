package ppak.cret.weather.types;

import ppak.cret.weather.WeatherArguments;

/**
 * Class representing clear sky weather
 * It stores all data about conditions, location and an icon for GUI.
 * Extends WeatherType class.
 * @see ppak.cret.weather.Weather
 * @see WeatherType
 * @see WeatherArguments
 * @see ppak.cret.weather.WeatherFactory
 */
public class ClearSkyWeather extends WeatherType {
    /**
     * Constructor initializing the object with given arguments
     * @param args WeatherArguments object describing conditions
     * @see WeatherArguments
     */
    public ClearSkyWeather(WeatherArguments args) {
        super(args);
    }

    @Override
    protected void setIconId() {
        this.iconId = "clearsky";
    }
}
