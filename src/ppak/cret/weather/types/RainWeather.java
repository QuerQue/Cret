package ppak.cret.weather.types;

import ppak.cret.weather.WeatherArguments;

/**
 * Class representing rainy weather
 * It stores all data about conditions, location and an icon for GUI.
 * Extends WeatherType class.
 * @see ppak.cret.weather.Weather
 * @see WeatherType
 * @see WeatherArguments
 * @see ppak.cret.weather.WeatherFactory
 */
public class RainWeather extends WeatherType {
    /**
     * Constructor initializing the object with given arguments
     * @param args WeatherArguments object describing conditions
     * @see WeatherArguments
     */
    public RainWeather(WeatherArguments args) {
        super(args);
    }

    @Override
    protected void setIconId() {
        this.iconId = "rain";
    }
}
