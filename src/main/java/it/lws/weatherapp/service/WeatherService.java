package it.lws.weatherapp.service;

import it.lws.weatherapp.exception.CityNotFoundException;
import it.lws.weatherapp.exception.ServiceNotAvailabeleException;
import it.lws.weatherapp.model.WeatherForecastDescription;
import it.lws.weatherapp.model.WeatherForecastDescriptions;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final WeatherParser parser;

    private static final String API_KEY = "b64102f327b1ea647b1c8e508bafac87";
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/forecast?q=%s&lang=de&units=metric&appid=%s";

    public WeatherForecastDescriptions getWeatherData(String city, int days) {
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = String.format(WEATHER_URL, city.replaceAll(" ", "+"), API_KEY);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        WeatherForecastDescriptions weatherForecastDescriptions = new WeatherForecastDescriptions(city);

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new CityNotFoundException("Failed to get data from openweather data");
            } else {
                JSONObject weatherData = new JSONObject(response.body());
                JSONArray list = weatherData.getJSONArray("list");
                int weatherForecastDaysCount = days;
                String previousDate = "";


                for (int i = 0; i < list.length(); i++) {
                    if (weatherForecastDaysCount == 0) {
                        break;
                    }
                    // "2022-10-27 15:00:00" 0-9
                    String date = list.getJSONObject(i).getString("dt_txt").substring(0, 10);
                    String[] dateTokens = date.split("-");
                    date = String.format("%s.%s.%s", dateTokens[2], dateTokens[1], dateTokens[0]);
                    if (!previousDate.equals(date)) {
                        String description = parser.parseDescription(list.getJSONObject(i));
                        String temperature = parser.parseTemperature(list.getJSONObject(i));

                        WeatherForecastDescription weatherForecastDescription = WeatherForecastDescription.builder()
                                .date(date)
                                .description(description)
                                .temperature(temperature)
                                .build();
                        weatherForecastDescriptions.addWeatherDescription(weatherForecastDescription);
                        weatherForecastDaysCount--;
                    }
                    previousDate = date;
                }
            }
        } catch (IOException | InterruptedException ex) {
            throw new ServiceNotAvailabeleException("Can't connect to server");
        }
        return weatherForecastDescriptions;
    }
}

