import java.util.ArrayList;
import java.util.List;

public class WeatherData implements Subject{
    private List<Observer> observers;
    private float temperture;
    private float humidity;
    private float pressure;

    public WeatherData()
    {
        observers = new ArrayList<Observer>();
    }
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for(Observer observer : observers)
        {
            observer.update(temperture, humidity, pressure);
        }
    }
    public void measurementsChange()
    {
        notifyObserver();
    }
    public void setMeasurements(float temperture, float humidity, float pressure)
    {
        this.temperture = temperture;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChange();
    }
}
