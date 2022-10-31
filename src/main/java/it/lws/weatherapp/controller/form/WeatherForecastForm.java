package it.lws.weatherapp.controller.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class WeatherForecastForm {
    private String city;
    private String days;
}
