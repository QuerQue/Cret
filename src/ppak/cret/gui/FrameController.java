package ppak.cret.gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import ppak.cret.connector.ApiDataProvider;
import ppak.cret.weather.Weather;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Application's Main Window Controller.
 */
public class FrameController {
    private ApiDataProvider apiDataProvider;
    private Weather currentWeather;

    @FXML private ImageView imageView;
    @FXML private Label locationName;
    @FXML private Label tempValue;
    @FXML private Label humidityValue;
    @FXML private Label pressureValue;
    @FXML private Label cloudsValue;
    @FXML private Label weatherDescription;
    /**
     * ComboBox containing all available cities.
     */
    @FXML public ComboBox<String> citiesCombo;
    /**
     * Currently selected City name.
     */
    public static String cityName;

    /**
     * Gets currently selected city name.
     * @return City name
     */
    public String getSelectedCity() {
        return citiesCombo.getSelectionModel().getSelectedItem();
    }

    /**
     * Starts weather data updates taking place each hour counting from launching application.
     */
    public void schedule() {
        Timeline updater = new Timeline(new KeyFrame(Duration.hours(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                reloadData();
            }
        }));
        updater.setCycleCount(Timeline.INDEFINITE);
        updater.play();
    }

    @FXML private void initData() {
        for (String city : apiDataProvider.getCitiesList()) {
            citiesCombo.getItems().add(city);
        }
        citiesCombo.getSelectionModel().select(0);
        reloadData();
    }

    /**
     * Gets ApiDataProvider reference
     * @return ApiDataProvider reference
     * @see ApiDataProvider
     */
    public ApiDataProvider getApiDataProvider() {
        return apiDataProvider;
    }

    /**
     * Initializes controller.
     */
    @FXML public void initialize() {
        apiDataProvider = new ApiDataProvider();
        initData();
        schedule();
    }

    @FXML private void citySelection() {
        reloadData();
    }

    /**
     * Gets new data from Web Service. This method is called every hour from start of application.
     */
    @FXML public void reloadData() {
        currentWeather = apiDataProvider.getNewData(citiesCombo.getSelectionModel().getSelectedItem());
        imageView.setImage(currentWeather.getWeatherIcon());
        locationName.setText(currentWeather.getLocation());
        locationName.setTextAlignment(TextAlignment.RIGHT);
        tempValue.setText(currentWeather.getTemperature() + " celsius");
        pressureValue.setText(currentWeather.getPressure() + " hPa");
        cloudsValue.setText(currentWeather.getClouds() + "%");
        humidityValue.setText(currentWeather.getHumidity()+"%");
        weatherDescription.setText(currentWeather.getDescription());
        cityName = locationName.getText();
    }

    @FXML private void historyClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("chartsFrame.fxml"));
            ChartFrameController controller = fxmlLoader.getController();
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Cret - history");
            stage.setScene(new Scene(root));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
