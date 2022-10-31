package it.lws.weatherapp.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherForecastDescription {
    private String temperature;
    private String date;
    private String description;
}
