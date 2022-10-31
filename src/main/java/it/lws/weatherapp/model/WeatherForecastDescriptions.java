package it.lws.weatherapp.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WeatherForecastDescriptions {
    private String city;
    private List<WeatherForecastDescription> weatherForecastDescriptions = new ArrayList<>();


    public WeatherForecastDescriptions(String city) {
        this.city = city;
    }

    public WeatherForecastDescription addWeatherDescription(WeatherForecastDescription weatherForecastDescription) {
        weatherForecastDescriptions.add(weatherForecastDescription);
        return weatherForecastDescription;
    }
}
