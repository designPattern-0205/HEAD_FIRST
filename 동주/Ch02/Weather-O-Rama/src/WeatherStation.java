// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class WeatherStation {
    public static void main(String[] args) {
            WeatherData weatherData = new WeatherData();

            CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
            ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);

            weatherData.setMeasurements(10, 20, 30.0f);
            weatherData.setMeasurements(39, 23, 11.0f);
            weatherData.setMeasurements(76, 33, 55.5f);
    }
}