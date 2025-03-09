package com.clt.weather_data_api;

import ch.qos.logback.classic.pattern.ClassOfCallerConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherCotroller {
    public final WeatherService weatherService;

    public WeatherCotroller(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public WeatherResponseDTO getWeather(@RequestParam String location){
        return weatherService.getWeather(location);
    }
}
