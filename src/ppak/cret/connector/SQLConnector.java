package ppak.cret.connector;

import ppak.cret.weather.Weather;
import ppak.cret.weather.WeatherArchive;
import ppak.cret.weather.WeatherArguments;

import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *Provides connection with database, adds record to database and creates WeatherArchive
 * @see ppak.cret.weather.WeatherArchive
 */
public class SQLConnector
{
    private Connection connection;

    /**
     * Constructor initializing the object nad establishing a connection with database
     */
    public SQLConnector() {
        try
        {
            Class.forName("java.sql.Driver");
            connection = DriverManager.getConnection("jdbc:sqlite:" + System.getProperty("user.dir")+"//database//weatherDB.db");
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }

    /**
     * Creates DateStringCreator object and based on that and given argument inserts new record into database
     * @param weather WeatherArguments object describing current weather
     * @see ppak.cret.weather.WeatherArguments
     * @see DateStringCreator
     */
    public void AddRecordToDB(WeatherArguments weather)
    {

        String insert = "INSERT INTO WeatherInfo VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(insert);

            statement.setString(1, weather.getLocation());
            statement.setString(2, Double.toString(weather.getTemperature()));
            statement.setString(3, Double.toString(weather.getPressure()));
            statement.setString(4, Double.toString(weather.getHumidity()));
            statement.setString(5, Double.toString(weather.getClouds()));
            statement.setString(6, weather.getDescription());
            statement.setString(7, new DateStringCreator().getCurrentDate());
            statement.executeUpdate();

        }catch(Exception e)
        {
            System.out.println(e);
        }
    }

    /**
     * Selects records from database and base on them  creates and returns WeatherArchive type object.
     * @param city String object representing a city for which records from database are selected
     * @return WeatherArchive type object containing all data about past weather conditions for given city
     * @see ppak.cret.weather.WeatherArchive
     */
    public WeatherArchive getArchiveData(String city)
    {
        WeatherArchive weatherArchive = new WeatherArchive();
        try
        {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM WeatherInfo WHERE City LIKE ?");
            ps.setString(1, city);
            ResultSet rs = ps.executeQuery();


            while (rs.next())
            {
                weatherArchive.getTemperatureList().add(rs.getDouble(2));
                weatherArchive.getPressureList().add(rs.getDouble(3));
                weatherArchive.getHumidityList().add(rs.getDouble(4));
                weatherArchive.getCloudsList().add(rs.getDouble(5));
                weatherArchive.getDescriptionList().add(rs.getString(6));
                weatherArchive.getDateList().add(rs.getString(7));
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return weatherArchive;
    }


}
