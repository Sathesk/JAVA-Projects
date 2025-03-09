package com.clt.weather_data_api;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WeatherService {
//    private final WebClient webClient;
    private final WebClient weatherWebClient;
    private final WebClient geoWebClient;

    //consturctor injection
    public WeatherService(WebClient weatherWebClient, WebClient geoWebClient){
        this.weatherWebClient = weatherWebClient;
        this.geoWebClient = geoWebClient;
    }

    //step1: convert location name to lat/lon
    public GeoCodingResponseDTO.Location getCoordinates(String location){
        GeoCodingResponseDTO response = geoWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/v1/search").queryParam("name",location)
                        .queryParam("count",1).queryParam("language","en")
                        .build()).retrieve().bodyToMono(GeoCodingResponseDTO.class).block();
                return (response != null && response.getResults().isEmpty()) ? response.getResults().get(0):null;
    }


//    public WeatherService(WebClient.Builder webClientBuilder){
//        this.webClient = webClientBuilder.baseUrl("https://api.open-meteo.com").build();
//    }

    //step 2: Get weather data using alt and longitude
    public WeatherResponseDTO getWeather(String location){

        GeoCodingResponseDTO.Location coordinates = getCoordinates(location);
        if(coordinates == null){
            return null;
        }

        //map json to weather response
        WeatherAPIResponseDTO reponse = weatherWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/v1/forecast")
                .queryParam("latitude",coordinates.getLatitude())
                .queryParam("longitude",coordinates.getLongitude())
                .queryParam("current_weather","true").build())
                .retrieve().bodyToMono(WeatherAPIResponseDTO.class).block();

        WeatherResponseDTO weatherResponse = reponse.getCurrentWeather();


        //add weather Description and Emoji
        weatherResponse.setWeatherDescription(getWeatherDescription(weatherResponse.getWeatherCode()));
        weatherResponse.setWeatherIcon(getWeatherIcon(weatherResponse.getWeatherCode()));

        return weatherResponse;
    }
    //map weather code to description
    private String getWeatherDescription(int code){
        Map<Integer, String> weatherDescriptions  = Map.ofEntries(
                Map.entry(0, "Clear sky"),
                Map.entry(1, "Mainly clear"),
                Map.entry(2, "Partly cloudy"),
                Map.entry(3, "Overcast"),
                Map.entry(45, "Fog"),
                Map.entry(48, "Depositing rime fog"),
                Map.entry(51, "Light drizzle"),
                Map.entry(53, "Moderate drizzle"),
                Map.entry(55, "Heavy drizzle"),
                Map.entry(56, "Freezing drizzle"),
                Map.entry(57, "Heavy freezing drizzle"),
                Map.entry(61, "Light rain"),
                Map.entry(63, "Moderate rain"),
                Map.entry(65, "Heavy rain"),
                Map.entry(80, "Rain showers"),
                Map.entry(81, "Heavy rain showers"),
                Map.entry(82, "Violent rain showers"),
                Map.entry(95, "Thunderstorm"),
                Map.entry(96, "Thunderstorm with hail"),
                Map.entry(99, "Severe thunderstorm with hail")
        );
        return weatherDescriptions.getOrDefault(code,"Unknown weather");
    }
    //Map weather code to Emoji icon
    private String getWeatherIcon(int code){
        Map<Integer, String> weatherIcons = Map.ofEntries(
                Map.entry(0, "☀️"),  // Clear sky
                Map.entry(1, "🌤️"),  // Mainly clear
                Map.entry(2, "⛅"),   // Partly cloudy
                Map.entry(3, "☁️"),  // Overcast
                Map.entry(45, "🌫️"), // Fog
                Map.entry(48, "🌁"),  // Depositing rime fog
                Map.entry(51, "🌦️"), // Light drizzle
                Map.entry(53, "🌧️"), // Moderate drizzle
                Map.entry(55, "🌧️"), // Heavy drizzle
                Map.entry(56, "🌨️"), // Freezing drizzle
                Map.entry(57, "❄️"),  // Heavy freezing drizzle
                Map.entry(61, "🌧️"), // Light rain
                Map.entry(63, "🌧️"), // Moderate rain
                Map.entry(65, "🌧️"), // Heavy rain
                Map.entry(80, "🌦️"), // Rain showers
                Map.entry(81, "🌧️"), // Heavy rain showers
                Map.entry(82, "⛈️"), // Violent rain showers
                Map.entry(95, "🌩️"), // Thunderstorm
                Map.entry(96, "⛈️"), // Thunderstorm with hail
                Map.entry(99, "⛈️")  // Severe thunderstorm with hail
        );
        return weatherIcons.getOrDefault(code,"?");
    }
}
//https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&current=temperature_2m,weathercode

//WeatherAPIResponseDTO reponse = webClient.get()
//        .uri(uriBuilder -> uriBuilder.path("/v1/forecast")
//                .queryParam("latitude",latitude)
//                .queryParam("longitude",longitude)
//                .queryParam("current_weather","true").build())
//        .retrieve().bodyToMono(WeatherAPIResponseDTO.class).block();
//
//WeatherResponseDTO weatherResponse = reponse.getCurrentWeather();
//        return weatherResponse;