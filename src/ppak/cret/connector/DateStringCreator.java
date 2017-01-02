package ppak.cret.connector;

import java.text.SimpleDateFormat;

/**
 * Represents current date as String object in format "yyyy-MM-dd HH:mm"
 * SQLConnector uses this class to create WeatherArchive object
 * @see SQLConnector
 * @see ppak.cret.weather.WeatherArchive
 */
public class DateStringCreator {
    private String CurrentDate;

    /**
     * Constructor initializing the object and setting current date
     */
    public DateStringCreator() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        java.util.Date now = new java.util.Date();
        CurrentDate = sdfDate.format(now);
    }

    /**
     * Gets current date
     * @return String object representing current date in format "yyyy-MM-dd HH:mm"
     */
    public String getCurrentDate() {
        return CurrentDate;
    }

    /**
     * Sets current date
     * @param currentDate as String object
     */
    public void setCurrentDate(String currentDate) {
        CurrentDate = currentDate;
    }
}
