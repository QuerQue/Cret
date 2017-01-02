package ppak.cret.connector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ppak.cret.weather.Weather;
import ppak.cret.weather.WeatherArguments;
import ppak.cret.weather.WeatherFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class providing list of cities and current weather information to FrameController
 * @see ppak.cret.gui.FrameController
 */
public class ApiDataProvider {

    private final String connectionString = "http://api.openweathermap.org/data/2.5/weather?q=";
    private final String apiId = "&appid=55237a65b62250166540d4f210729e08";
    private final String units = "&units=metric";
    private List<String> citiesList;

    /**
     * Gets all cities for which FrameController creates record in combox
     * List of cities contains "London,uk","Cracow,pl", "NewYork,us" as default values
     * @return list of cities
     * @see ppak.cret.gui.FrameController
     */
    public List<String> getCitiesList() {
        return citiesList;
    }

    /**
     * Add new city for which FrameController creates record in combox.
     * @param city String object representing city's name
     * @see ppak.cret.gui.FrameController
     */
    public void addCity(String city) {
        citiesList.add(city);
    }

    private String getFullString(String city){
        return connectionString + city + apiId + units;
    }

    /**
     *Constructor initializing the object and setting list of cities with default values
     */
    public ApiDataProvider(){
        citiesList = new ArrayList<String>();
        citiesList.add("London,uk");
        citiesList.add("Cracow,pl");
        citiesList.add("NewYork,us");
    }

    /**
     * Establishes connection with OpenWeatherMap service, downloads current weather information for @param city
     * and base on this creates and returns new Weather object. Later FrameController uses this object
     * to reload data.
     * @param city for which weather information should be download
     * @return Weather object based on current weather information for @param city
     * @see ppak.cret.weather.Weather
     * @see ppak.cret.gui.FrameController
     */
    public Weather getNewData(String city) {

        Weather weather = null;
        String result = "";
        try {
            URL url_weather = new URL(getFullString(city));

            HttpURLConnection httpURLConnection = (HttpURLConnection) url_weather.openConnection();

            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                InputStreamReader inputStreamReader =
                        new InputStreamReader(httpURLConnection.getInputStream());
                BufferedReader bufferedReader =
                        new BufferedReader(inputStreamReader, 8192);
                String line = null;
                while((line = bufferedReader.readLine()) != null){
                    result += line;
                }

                bufferedReader.close();

                weather  = generateWeather(result, city);


            } else {
                System.out.println("Error in httpURLConnection.getResponseCode()!!!");
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(ApiDataProvider.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ApiDataProvider.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(ApiDataProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return weather;
    }

    private Weather generateWeather(String json, String city) throws JSONException{

        JSONObject jsonObject = new JSONObject(json);

        //"weather"
        String result_weather;
        JSONArray weatherArray = jsonObject.getJSONArray("weather");
        String description = null;
        String iconId = null;
        if(weatherArray.length() > 0){
            JSONObject weatherJson = weatherArray.getJSONObject(0);
            description = weatherJson.getString("description");
            String fullIconString = weatherJson.getString("icon");
            iconId = fullIconString.substring(0, Math.min(fullIconString.length(), 2));
        }

        //"main"
        JSONObject mainJson = jsonObject.getJSONObject("main");
        double temperature = mainJson.getDouble("temp");
        double pressure = mainJson.getDouble("pressure");
        double humidity = mainJson.getDouble("humidity");

        //"clouds"
        JSONObject JSONObject_clouds = jsonObject.getJSONObject("clouds");
        double clouds = JSONObject_clouds.getInt("all");
        WeatherArguments args = new WeatherArguments(temperature, pressure, humidity, clouds, description, city);

        SQLConnector con = new SQLConnector();
        con.AddRecordToDB(args);

        return WeatherFactory.getInstance().getWeather(iconId, args);
    }
}
