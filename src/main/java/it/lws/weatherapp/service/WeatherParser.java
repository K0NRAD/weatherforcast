package it.lws.weatherapp.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class WeatherParser {

    public String parseDescription(JSONObject weatherData) {
        return weatherData.getJSONArray("weather")
                .getJSONObject(0)
                .getString("description");
    }

    public String parseTemperature(JSONObject weatherData) {
        Float temperature = weatherData.getJSONObject("main")
                .getFloat("temp");
        return String.format("%3.1f°C", temperature);
    }
}
