package it.lws.weatherapp.controller;

import it.lws.weatherapp.model.WeatherForecastDescriptions;
import it.lws.weatherapp.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weatherforecast")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping
    public WeatherForecastDescriptions getWeatherDatas(@RequestParam String city, @RequestParam int days) {
        return weatherService.getWeatherData(city, days);
    }


}
