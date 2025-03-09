package com.clt.weather_data_api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true) //ignore unused things when parsing is happen
@Data
public class WeatherResponseDTO {

    @JsonProperty("temperature")
    private String temperature;

    @JsonProperty("windspeed")
    private double windSpeed;

    @JsonProperty("weathercode")
    private int weatherCode;

    @JsonProperty("time")
    private String time;


    private String weatherDescription;
    private String WeatherIcon;
}
