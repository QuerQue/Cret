package ppak.cret.weather.types;
import javafx.scene.image.Image;
import ppak.cret.weather.Weather;
import ppak.cret.weather.WeatherArguments;

/**
 * Base class for all Weather types supported by Cret application.
 * It stores all data about conditions, location and an icon for GUI.
 * Implements Weather interface.
 * @see Weather
 * @see WeatherArguments
 * @see ppak.cret.weather.WeatherFactory
 */
public abstract class WeatherType implements Weather {

    protected WeatherArguments arguments;
    protected Image weatherIcon;
    protected String iconId;

    protected abstract void setIconId();

    protected void setWeatherIcon(){
        this.weatherIcon = new Image("file:" + System.getProperty("user.dir")+ "\\resources\\"+ iconId + ".png");
    }

    @Override
    public String getLocation() {
        return arguments.getLocation();
    }

    protected WeatherType(WeatherArguments args) {
        setIconId();
        setWeatherIcon();
        arguments = args;
    }

    @Override
    public double getTemperature() {
        return arguments.getTemperature();
    }

    @Override
    public double getPressure() {
        return arguments.getPressure();
    }

    @Override
    public double getHumidity() {
        return arguments.getHumidity();
    }

    @Override
    public double getClouds() {
        return arguments.getClouds();
    }

    @Override
    public Image getWeatherIcon() {
        return weatherIcon;
    }

    @Override
    public String getDescription() {
        return arguments.getDescription();
    }
}
