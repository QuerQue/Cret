package ppak.cret.weather;

import ppak.cret.weather.types.*;

/**
 * Factory using Factory and Singleton design patterns.
 * Produces Weather type objects based upon provided iconId and arguments.
 * @see Weather
 * @see WeatherType
 * @see WeatherArguments
 */
public class WeatherFactory {

    private static WeatherFactory instance = null;

    /**
     * Creates instance or returns a reference to already existing one
     * @return instance of WeatherFactory
     */
    public static WeatherFactory getInstance() {
        if(instance == null) {
            instance = new WeatherFactory();
        }
        return instance;
    }

    private WeatherFactory(){};

    /**
     * Gets Weather type objects based upon given arguments and iconId read from web service's API.
     * @param iconId id read from web service's API
     * @param args WeatherArguments object describing current weather
     * @return Weather type object containing all data about weather conditions
     * @see Weather
     * @see WeatherType
     * @see WeatherArguments
     * @see ppak.cret.connector.ApiDataProvider
     */
    public Weather getWeather(String iconId, WeatherArguments args){

        switch(iconId)
        {
            case "01":
                return new ClearSkyWeather(args);
            case "02":
                return new FewCloudsWeather(args);
            case "03":
                return new ScatteredCloudsWeather(args);
            case "04":
                return new BrokenCloudsWeather(args);
            case "09":
                return new ShowerRainWeather(args);
            case "10":
                return new RainWeather(args);
            case "11":
                return new ThunderstormWeather(args);
            case "13":
                return new SnowWeather(args);
            case "50":
                return new MistWeather(args);
        }
        return null;
    }
}
