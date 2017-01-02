package ppak.cret.gui;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import ppak.cret.connector.SQLConnector;
import ppak.cret.weather.WeatherArchive;

import java.util.List;

/**
 * Charts Frame Controller.
 */
public class ChartFrameController {

    @FXML private Label locationName;
    @FXML private BarChart<String, Double> pressureChart;
    @FXML private BarChart<String, Double> tempChart;
    @FXML private BarChart<String, Double> cloudsChart;
    @FXML private BarChart<String, Double> humidityChart;

    /**
     * Initializes the controller.
     */
    @FXML public void initialize() {
        tempChart.setLegendVisible(false);
        pressureChart.setLegendVisible(false);
        cloudsChart.setLegendVisible(false);
        humidityChart.setLegendVisible(false);

        initData();
    }

    @FXML private void initData() {
        String cityName = FrameController.cityName;
        locationName.setText(cityName);

        SQLConnector sqlConnector = new SQLConnector();
        WeatherArchive weatherArchive = sqlConnector.getArchiveData(cityName);

        List<String> dateList = weatherArchive.getDateList();
        int recordNo = dateList.toArray().length;

        XYChart.Series tempSeries = new XYChart.Series();
        XYChart.Series pressureSeries = new XYChart.Series();
        XYChart.Series cloudsSeries = new XYChart.Series();
        XYChart.Series humiditySeries = new XYChart.Series();

        for(int i = Math.max(0, recordNo-24); i < recordNo; i++) {
            tempSeries.getData().add(new XYChart.Data(dateList.get(i), weatherArchive.getTemperatureList().get(i)));
            pressureSeries.getData().add(new XYChart.Data(dateList.get(i), weatherArchive.getPressureList().get(i)));
            cloudsSeries.getData().add(new XYChart.Data(dateList.get(i), weatherArchive.getCloudsList().get(i)));
            humiditySeries.getData().add(new XYChart.Data(dateList.get(i), weatherArchive.getHumidityList().get(i)));
        }
        tempChart.getData().add(tempSeries);
        pressureChart.getData().add(pressureSeries);
        cloudsChart.getData().add(cloudsSeries);
        humidityChart.getData().add(humiditySeries);
    }

}
