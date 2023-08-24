public class ForecastDisplay implements Observer, DisplayElement{
    private float currentTemperature;
    private String forecast;
    private WeatherData weatherData;
    public ForecastDisplay(WeatherData weatherData)
    {
        this.weatherData = weatherData;
        this.currentTemperature = 30.0f;
        weatherData.registerObserver(this);
    }
    @Override
    public void display() {
        System.out.println("기상 예보 : " + forecast);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        if (currentTemperature < temperature)
        {
            forecast = "날씨가 좋아지고 있습니다.";
        }
        else
        {
            forecast = "날씨가 왜이러지...";
        }
        display();
    }
}
