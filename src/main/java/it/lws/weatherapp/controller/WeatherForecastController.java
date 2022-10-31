package it.lws.weatherapp.controller;

import it.lws.weatherapp.controller.form.WeatherForecastForm;
import it.lws.weatherapp.exception.CityNotFoundException;
import it.lws.weatherapp.exception.ServiceNotAvailabeleException;
import it.lws.weatherapp.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/weatherforecast")
@RequiredArgsConstructor
public class WeatherForecastController {
    private final WeatherService weatherService;

    @GetMapping
    public ModelAndView getWeatherForecast(
            ModelMap model,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Integer days) {

        if (city != null && days != null) {
            try {
                model.addAttribute("weatherForecastDescriptions", weatherService.getWeatherData(city, days));
            } catch (ServiceNotAvailabeleException ex) {
                model.addAttribute("error", "Weather service not available");
            } catch (CityNotFoundException ex) {
                model.addAttribute("error", "City not found");
            }
        }
        model.addAttribute("weatherForecastForm", new WeatherForecastForm());
        return new ModelAndView("weatherforecast", model);
    }

    ;
}
