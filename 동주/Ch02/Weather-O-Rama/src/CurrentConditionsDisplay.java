public class CurrentConditionsDisplay implements Observer, DisplayElement{
    private float currentTemperature;
    private float currentHumidity;
    private WeatherData weatherdata;

    public CurrentConditionsDisplay(WeatherData weatherdata)
    {
        this.weatherdata = weatherdata;
        weatherdata.registerObserver(this);
    }
    @Override
    public void display() {
        System.out.println("현재 상태 : 온도 : " + currentTemperature + ", 습도 : " + currentHumidity);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        currentTemperature = temperature;
        currentHumidity = humidity;
        display();
    }
}
